package com.github.light.bson;

import com.github.light.bson.util.BufferingBsonOutputStream;
import com.github.light.bson.util.BsonInputStream;
import com.github.light.bson.util.BsonOutputStream;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by rob on 21-12-14.
 */
public class BsonStreamTest {

    @Test
    public void testStringSerialization() throws IOException {
        testStringSerialization("");
        testStringSerialization("foo");
        testStringSerialization("bar");
        testStringSerialization("bar");
        testStringSerialization("ᚠᛇᚻ᛫ᛒᛦᚦ᛫ᚠᚱᚩᚠᚢᚱ᛫ᚠᛁᚱᚪ᛫ᚷᛖᚻᚹᛦᛚᚳᚢᛗ Τη γλώσσα μου έδωσαν ελληνική");
    }

    @Test
    public void testInteger() throws IOException {
        testIntegerSerialization(0);
        testIntegerSerialization(1);
        testIntegerSerialization(-1);
        testIntegerSerialization(Integer.MAX_VALUE);
        for (int i = Integer.MIN_VALUE; i < Integer.MIN_VALUE + 100000; i++) {
            testIntegerSerialization(i);
        }
        for (int i = Integer.MAX_VALUE - 100000; i < Integer.MAX_VALUE; i++) {
            testIntegerSerialization(i);
        }
    }

    @Test
    public void testLong() throws IOException {
        testLongSerialization(0);
        testLongSerialization(1);
        testLongSerialization(-1);
        testLongSerialization(100000000000L);
        testLongSerialization(Integer.MAX_VALUE);
        testLongSerialization(Integer.MIN_VALUE);
        testLongSerialization(Long.MAX_VALUE);
        for (long i = Long.MIN_VALUE; i < Long.MIN_VALUE + 100000; i++) {
            testLongSerialization(i);
        }
        for (long i = Long.MAX_VALUE - 100000; i < Long.MAX_VALUE; i++) {
            testLongSerialization(i);
        }
    }

    @Test
    public void testDouble() throws IOException {
        testDoubleSerialization(0);
        testDoubleSerialization(-1);
        testDoubleSerialization(1);
        testDoubleSerialization(1.1);
        testDoubleSerialization(1.000001);
        testDoubleSerialization(-1.000001);
        testDoubleSerialization(Double.MAX_VALUE);
        testDoubleSerialization(Double.MIN_VALUE);
        testDoubleSerialization(Math.PI);
        testDoubleSerialization(Math.E);
    }

    private void testLongSerialization(long value) throws IOException {
        this.<Long>testSerialization(value, new InOut<Long>() {

            @Override
            public void writeOn(BsonOutputStream out, Long value) throws IOException {
                out.writeLong(value);
            }

            @Override
            public Long readFrom(BsonInputStream in) throws IOException {
                return in.readLong();
            }
        });
    }

    private void testDoubleSerialization(double value) throws IOException {
        testSerialization(value, new InOut<Double>() {

            @Override
            public void writeOn(BsonOutputStream out, Double value) throws IOException {
                out.writeDouble(value);
            }

            @Override
            public Double readFrom(BsonInputStream in) throws IOException {
                return in.readDouble();
            }
        });
    }

    private void testIntegerSerialization(int value) throws IOException {
        testSerialization(value, new InOut<Integer>() {

            @Override
            public void writeOn(BsonOutputStream out, Integer value) throws IOException {
                out.writeInt(value);
            }

            @Override
            public Integer readFrom(BsonInputStream in) throws IOException {
                return in.readInt();
            }
        });
    }

    private void testStringSerialization(String value) throws IOException {
        testSerialization(value, new InOut<String>() {

            @Override
            public void writeOn(BsonOutputStream out, String value) throws IOException {
                out.writeString(value);
            }

            @Override
            public String readFrom(BsonInputStream in) throws IOException {
                return in.readString();
            }
        });
    }

    private <V> void testSerialization(V value, InOut<V> inOut) throws IOException {
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        BsonOutputStream bsonOut = new BufferingBsonOutputStream(bytesOut);
        inOut.writeOn(bsonOut, value);
        bsonOut.close();
        ByteArrayInputStream bytesIn = new ByteArrayInputStream(bytesOut.toByteArray());
        BsonInputStream bsonIn = new BsonInputStream(bytesIn);
        V readValue = inOut.readFrom(bsonIn);
        Assert.assertEquals(value, readValue);
    }

    private interface InOut<V> {
        void writeOn(BsonOutputStream out, V value) throws IOException;
        V readFrom(BsonInputStream in) throws IOException;
    }
}
