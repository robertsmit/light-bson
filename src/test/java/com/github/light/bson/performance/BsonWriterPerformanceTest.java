package com.github.light.bson.performance;

import com.fasterxml.jackson.core.JsonGenerator;
import de.undercouch.bson4jackson.BsonGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by rob on 12-10-14.
 */
public class BsonWriterPerformanceTest extends AbstractPerformanceTest {

    @Before
    public void setUp() {
        super.setUp();
        System.gc();
    }

    @Test
    public void test() throws IOException {
        assertBsonEquals(generateOther(), generateMine());
        int iterations = 90000;

        System.gc();
        long startOther = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            generateOther();
        }
        long durationOther = System.currentTimeMillis() - startOther;

        System.gc();
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
        BsonGenerator generator = bson4jackson.createGenerator(expectedOut);
        writeBson(generator);
        generator.close();
        return expectedOut.toByteArray();
    }

    private byte[] generateMine() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(0);
        JsonGenerator generator = ag5Bson.createGenerator(out);
        writeBson(generator);
        generator.close();
        return out.toByteArray();
    }


    protected void writeBson(JsonGenerator generator) throws IOException {
        writeBson(generator, 4);
    }

    protected void writeBson(JsonGenerator generator, int i) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("vifddddddddddfasdfafdasadfasfdasdfadsasdfsadfasf", "value1String");
        generator.writeStringField("value1", "value1String");
        generator.writeStringField("value2", "value2String");
        generator.writeNumberField("valueI64", 101201211121212L);
        generator.writeNumberField("valueI32Max", Integer.MAX_VALUE);
        generator.writeNumberField("valueI32Min", Integer.MIN_VALUE);
        generator.writeNumberField("valueShort", (short) 123);
        generator.writeBooleanField("valueBoolean", true);
        generator.writeNullField("valueNull");

        generator.writeFieldName("valueSubObject1");
        writeBsonPerson(generator, "Els", 33);

        generator.writeFieldName("valueSubObject2");
        writeBsonPerson(generator, "Els", 33);

        if (i > 0) {
            generator.writeFieldName("valueRecurObject");
            writeBson(generator, i - 1);
        }

        generator.writeArrayFieldStart("valueStringArray");
        for (String s : new String[] { "a", "b", "c", "d", "e", "f"}) {
            generator.writeString(s);
        }
        generator.writeEndArray();

        generator.writeArrayFieldStart("valueDocumentArray");
        for (String s : new String[] { "Karl", "Els" }) {
            writeBsonPerson(generator, s, s.hashCode());
        }
        generator.writeEndArray();
        generator.writeEndObject();

    }

    protected void writeBsonPerson(JsonGenerator generator, String name, int age) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("name", name);
        generator.writeNumberField("age", age);
        generator.writeEndObject();
    }
}
