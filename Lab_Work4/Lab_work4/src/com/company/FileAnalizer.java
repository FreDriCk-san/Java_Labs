package com.company;

//Letters - "[a-zA-Zа-яА-Я]"
//Words - "([^A-Za-zА-Яа-я])([\\s\\W]+)"

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAnalizer {

    private static ArrayList<Container> cache = new ArrayList<Container>();
    private static final Pattern wordsPattern = Pattern.compile("[\\s]+", Pattern.DOTALL);

    private String path;
    private Container container;

    public String getPath() {
        return path;
    }

    public FileAnalizer(String path) {

        if (null == path) {
            throw new NullPointerException("Nullable path");
        }

        this.path = path;

        for (Container container : cache) {
            if (path.equals(container.path)) {
                this.container = container;
                break;
            }
        }

        if (null == this.container) {

            container = new Container(path);
            cache.add(container);
        }

    }

    public int countLetters() {

        if (-1 == container.countLetters) {

            int count = 0;
            HashMap<Character, Integer> statistic = container.refreshStatistic();
            for (Character symbol : statistic.keySet()) {
                if (Character.isLetter(symbol)) {
                    count += statistic.get(symbol);
                }
            }

            container.countLetters = count;
        }
        else {
            container.refreshStatistic();
        }

        return container.countLetters;
    }

    public int countWords() {
        container.refreshStatistic();

        return container.countWords;
    }

    public int countLines() {
        container.refreshStatistic();

        return container.countLines;
    }

    public Map<Character, Integer> countFrequencyCharacteristic() {
        return container.refreshStatistic();
    }

    private class Container {

        public final String path;
        public long timestamp;

        public HashMap<Character, Integer> statistic;
        public int countWords;
        public int countLetters;
        public int countLines;

        private final File file;

        public Container(String path) {

            this.path = path;
            this.file = new File(path);
            this.timestamp = file.lastModified();

            try {
                String type = Files.probeContentType(file.toPath());
                if (!"text/plain".equals(type)) {
                    throw new IllegalArgumentException("Bad file type.");
                }
            } catch (IOException ioException) {
                throw new IllegalArgumentException("Can't check file extension.", ioException);
            }

            this.statistic = null;
            this.countWords = -1;
            this.countLetters = -1;
            this.countLines = -1;
        }

        public boolean isModified() {

            File file = new File(path);
            return timestamp != file.lastModified();
        }

        public HashMap<Character, Integer> refreshStatistic() {

            if (null == statistic) {
                analyze();
            }
            else if(isModified()) {
                statistic.clear();
                statistic = null;

                analyze();
            }

            return statistic;
        }

        private void analyze() {

            this.countWords = 0;
            this.countLines = 0;
            this.statistic = new HashMap<Character, Integer>();

            try {
                FileInputStream stream = new FileInputStream(path);
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                String line;
                while ((line = reader.readLine()) != null) {

                    this.countWords += countWordsIn(line);
                    this.countLines ++;
                    countCharactersTo(this.statistic, line);
                }

                reader.close();
            } catch (FileNotFoundException notFound) {
                throw new IllegalArgumentException("File not found.", notFound);
            } catch (IOException ioException) {
                throw new IllegalArgumentException("Problem with read file's content.", ioException);
            }
        }

        private int countWordsIn(String line) {

            int result = 0;

            Matcher matcher = wordsPattern.matcher(line);
            while (matcher.find()) {
                result++;
            }

            if (!"".equals(line)) {
                result++;
            }

            return result;
        }
        private void countCharactersTo(HashMap<Character, Integer> statistic, String line) {

            StringBuilder builder = new StringBuilder(line);
            builder.append('\n');

            for (Character symbol : builder.toString().toCharArray()) {

                Integer count = statistic.get(symbol);
                if (count != null) {
                    statistic.put(symbol, count + 1);
                } else {
                    statistic.put(symbol, 1);
                }
            }
        }
    }
}