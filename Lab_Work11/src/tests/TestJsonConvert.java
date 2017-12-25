package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import source.IJsonConvert;
import source.JsonConvert;
import source.options.Formatting;
import source.options.JsonConvertOptions;
import source.options.LineBreaks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TestJsonConvert {

    private static final String minifyJson = "../minifyJson.json";
    private static final String extendedJson = "../extendedJson.json";

    private TestObject instance;
    private IJsonConvert converter;

    @BeforeEach
    public void setup() {

        instance = new TestObject();
        TestObject reference = new TestObject();
        reference.notNullReference = new TestObject();
        instance.notNullReference = reference;

        instance.range.add(new TestObject());
        instance.range.add(reference);

        instance.map.put("f", new TestObject());
        instance.map.put("u", new TestObject());
        instance.map.put("c", new TestObject());
        instance.map.put("k", reference);

        converter = new JsonConvert();
    }

    @Test
    public void serializeObject() throws IOException {

        String content = converter.serializeObject(instance);
        writeToFile(content, minifyJson);

        JsonConvertOptions options = new JsonConvertOptions();
        options.jsonStyle = Formatting.Extended;
        options.lineBreaks = LineBreaks.Enable;

        content = converter.serializeObject(instance, options);
        writeToFile(content, extendedJson);
    }

    private void writeToFile(String content, String filename) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(content);
        writer.close();
    }
}
