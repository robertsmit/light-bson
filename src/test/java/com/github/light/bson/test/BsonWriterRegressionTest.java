package com.github.light.bson.test;

import com.github.light.bson.write.BsonBuffer;
import com.github.light.bson.write.SimpleBsonWriter;
import de.undercouch.bson4jackson.BsonFactory;
import de.undercouch.bson4jackson.BsonGenerator;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by rob on 12-10-14.
 */
public class BsonWriterRegressionTest {
    private static BsonFactory bsonFactory = new BsonFactory();

    @Before
    public void setUp() {
    }

    /**
        @see <a href="http://bsonspec.org/faq.html" />
     */
    @Test
    public void testExampleHelloWorld() throws IOException {
        ByteArrayOutputStream expectedOut = new ByteArrayOutputStream();
        BsonGenerator generator = bsonFactory.createGenerator(expectedOut);
        generator.writeStartObject();
        generator.writeFieldName("hello");
        generator.writeString("world");
        generator.writeEndObject();
        generator.close();
        byte[] expected = expectedOut.toByteArray();


        BsonBuffer actualOut = new BsonBuffer(256);
        SimpleBsonWriter writer = new SimpleBsonWriter(actualOut);
        writer
                .startObject()
                .field("hello")
                .writeString("world")
                .endObject();
        byte[] actual = actualOut.toByteArray();
        assertBsonEquals(expected, actual);


    }

    /**
     @see <a href="http://bsonspec.org/faq.html" />
     */
    @Test
    public void testExampleArray() throws IOException {
        ByteArrayOutputStream expectedOut = new ByteArrayOutputStream();
        BsonGenerator generator = bsonFactory.createGenerator(expectedOut);
        generator.writeStartObject();
        generator.writeFieldName("BSON");
        generator.writeStartArray();
        generator.writeString("awesome");
        generator.writeNumber(5.05);
        generator.writeNumber(1986);
        generator.writeEndArray();
        generator.writeEndObject();
        generator.close();
        byte[] expected = expectedOut.toByteArray();

        BsonBuffer actualOut = new BsonBuffer(256);
        SimpleBsonWriter writer = new SimpleBsonWriter(actualOut);
        writer
                .startObject()
                .field("BSON")
                .startArray()
                .writeString("awesome")
                .writeNumber(5.05)
                .writeNumber(1986)
                .endArray()
                .endObject();
        byte[] actual = actualOut.toByteArray();
        assertBsonEquals(expected, actual);
    }

    private void assertBsonEquals(byte[] expected, byte[] actual) {
        boolean condition = Arrays.equals(expected, actual);
        if (!condition) {
            System.out.println("__expected__");
            System.out.println(bytesToHexDisplay(expected));
            System.out.println("__actual__");
            System.out.println(bytesToHexDisplay(actual));
            throw new AssertionError();
        }
    }



    public static String bytesToHexDisplay(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : in) {
            if (b >= 65) {
                builder.append((char)b);
            }
            else {
                builder.append("|");
                builder.append(String.format("x%02X", b));
            }
        }
        return builder.toString();
    }



}
