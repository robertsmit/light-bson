package com.github.light.bson.test;

import com.github.light.bson.BsonWriter;
import com.github.light.bson.write.BsonBuffer;
import com.github.light.bson.write.CachingBsonBuffer;
import com.github.light.bson.write.SimpleBsonWriter;
import de.undercouch.bson4jackson.BsonFactory;
import de.undercouch.bson4jackson.BsonGenerator;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by rob on 12-10-14.
 */
public class BsonWriterPerformanceTest {
    private static BsonFactory bsonFactory = new BsonFactory();

    @Test
    public void test() throws IOException {
        assertBsonEquals(generateOther(), generateMine());
        int iterations = 10000;
        long startOther = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            generateOther();
        }
        long durationOther = System.currentTimeMillis() - startOther;

        long startMine = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            generateMine();
        }
        long durationMine = System.currentTimeMillis() - startMine;
        System.out.println("other---");
        System.out.println(durationOther);
        System.out.println("mine---");
        System.out.println(durationMine);
        System.out.println("perc---");
        long perc = (durationMine * 100) / durationOther;
        System.out.println(perc);
        Assert.assertTrue(perc < 70);
    }

    protected byte[] generateOther() throws IOException {
        ByteArrayOutputStream expectedOut = new ByteArrayOutputStream();
        BsonGenerator generator = bsonFactory.createGenerator(expectedOut);
        try {
            writeBson(new JacksonBsonWriter(generator));
        }
        finally {
            generator.close();
        }
        return expectedOut.toByteArray();
    }

    private byte[] generateMine() throws IOException {
        BsonBuffer actualOut = new CachingBsonBuffer(1024);
        writeBson(new SimpleBsonWriter(actualOut));
        return actualOut.toByteArray();
    }


    private void writeBson(BsonWriter writer) throws IOException {
        writeBson(writer, 4);
    }

    private void writeBson(BsonWriter writer, int i) throws IOException {
        writer
                .startObject()
                .writeStringField("value1", "value1String")
                .field("value2")
                .writeString("value2String")
                .field("valueI64")
                .writeNumber(101201211121212L)
                .field("valueI32Max")
                .writeNumber(Integer.MAX_VALUE)
                .field("valueI32Min")
                .writeNumber(Integer.MIN_VALUE)
                .field("valueShort")
                .writeNumber((short) 123)
                .field("valueBoolean")
                .writeBoolean(true)
                .field("valueNull")
                .writeNull();

        writer.field("valueSubObject1");
        writeBsonPerson(writer, "Els", 33);

        writer.field("valueSubObject2");
        writeBsonPerson(writer, "Els", 33);

        if (i > 0) {
            writer.field("valueRecurObject");
            writeBson(writer, i - 1);
        }

        writer.field("valueStringArray").startArray();
        for (String s : new String[] { "a", "b", "c", "d", "e", "f"}) {
            writer.writeString(s);
        }
        writer.endArray();

        writer.field("valueDocumentArray").startArray();
        for (String s : new String[] { "Karl", "Els" }) {
            writeBsonPerson(writer, s, s.hashCode());
        }
        writer.endArray();

        writer.endObject();

    }

    private void writeBsonPerson(BsonWriter writer, String name, int age) throws IOException {
        writer
                .startObject()
                .writeStringField("name", name)
                .field("age").writeNumber(age)
                .endObject();
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
}
