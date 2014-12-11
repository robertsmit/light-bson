package com.github.light.bson.regression;

import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by rob on 12-10-14.
 */
public class BsonWriterRegressionTest extends AbstractRegressionTest {


    /**
        @see <a href="http://bsonspec.org/faq.html" />
     */
    @Test
    public void testExampleHelloWorld() throws IOException {
        ByteArrayOutputStream expectedOut = new ByteArrayOutputStream();
        JsonGenerator generator = bson4jackson.createGenerator(expectedOut);
        writeExampleHelloWorld(generator);
        byte[] expected = expectedOut.toByteArray();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        generator = ag5Bson.createGenerator(out);
        writeExampleHelloWorld(generator);
        byte[] actual = out.toByteArray();
        assertBsonEquals(expected, actual);
    }


    public void writeExampleHelloWorld(JsonGenerator generator) throws IOException {
        generator.writeStartObject();
        generator.writeFieldName("hello");
        generator.writeString("world");
        generator.writeEndObject();
        generator.close();
    }

    /**
     @see <a href="http://bsonspec.org/faq.html" />
     */
    @Test
    public void testExampleArray() throws IOException {
        ByteArrayOutputStream expectedOut = new ByteArrayOutputStream();
        JsonGenerator generator = bson4jackson.createGenerator(expectedOut);
        writeExampleArray(generator);
        byte[] expected = expectedOut.toByteArray();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        generator = ag5Bson.createGenerator(out);
        writeExampleArray(generator);
        byte[] actual = out.toByteArray();

        assertBsonEquals(expected, actual);
    }

    public void writeExampleArray(JsonGenerator generator) throws IOException {
        generator.writeStartObject();
        generator.writeFieldName("BSON");
        generator.writeStartArray();
        generator.writeString("awesome");
        generator.writeNumber(5.05);
        generator.writeNumber(1986);
        generator.writeEndArray();
        generator.writeEndObject();
        generator.close();
    }
}
