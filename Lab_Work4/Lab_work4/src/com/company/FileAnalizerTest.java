package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

public class FileAnalizerTest {

    private final String smallFile;
    private final String largeFile;
    private final String imageFile;

    private FileAnalizer smallAnalizer;

    public FileAnalizerTest() {

        this.smallFile = "../small-file.txt";
        this.largeFile = "../large-file.txt";
        this.imageFile = "../image-file.png";
    }

    @Test
    public void checkContructor() {

        FileAnalizer small = new FileAnalizer(smallFile);
        Assertions.assertEquals(smallFile, small.getPath());

        FileAnalizer large = new FileAnalizer(largeFile);
        Assertions.assertEquals(largeFile, large.getPath());

        try {
            FileAnalizer image = new FileAnalizer(imageFile);
            Assertions.fail("Init FileAnalizer for image file.");
        }
        catch (IllegalArgumentException exception) {}
    }

    @BeforeEach
    public void init() {
        smallAnalizer = new FileAnalizer(smallFile);
    }

    @Test
    public void checkMethods() {

        Assertions.assertEquals(9, smallAnalizer.countLetters());
        Assertions.assertEquals(12, smallAnalizer.countWords());
        Assertions.assertEquals(10, smallAnalizer.countLines());

        HashMap<Character, Integer> statistic = new HashMap<Character, Integer>();
        statistic.put('1', 4);
        statistic.put('2', 3);
        statistic.put('3', 3);
        statistic.put('4', 2);
        statistic.put('5', 1);
        statistic.put('a', 3);
        statistic.put('b', 2);
        statistic.put('c', 1);
        statistic.put('l', 1);
        statistic.put('k', 1);
        statistic.put('m', 1);
        statistic.put('\n', 10);
        statistic.put(' ', 4);

        Assertions.assertEquals(statistic, smallAnalizer.countFrequencyCharacteristic());
    }

    @Test
    public void usingLargeFile() {

        for(int i = 0; i < 100; i++) {

            Assertions.assertNotEquals(-1, new FileAnalizer(largeFile).countLines());
        }
    }
    @Test
    public void checkCaching(){

        String path = "../test.txt";
        clearFile(path);
        for(int i = 0; i < 10000; i++) {

            if (0 == i % 5){
                clearFile(path);
            }
            else {
                setOneLine(path);
            }

            Assertions.assertEquals(i % 5,  new FileAnalizer(path).countLines());
        }
    }

    private void clearFile(String path) {
        setContent(path, "", false);
    }
    private void setOneLine(String path) {
        setContent(path, "One line\n", true);
    }
    private void setContent(String path, String content, boolean append) {

        try {
            File file = new File(path);
            FileWriter writer = new FileWriter(file, append);
            writer.write(content);
            writer.close();
        }
        catch (Exception exception) {}
    }

}