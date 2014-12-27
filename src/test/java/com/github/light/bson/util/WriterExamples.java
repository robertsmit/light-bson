package com.github.light.bson.util;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by rob on 21-12-14.
 */
public class WriterExamples {
    private WriterExamples() {

    }

    public static JsonWriter forHelloWorld() {
        return new JsonObjectWriter() {
            @Override
            public void writeBody(JsonGenerator generator) throws IOException {
                generator.writeFieldName("hello");
                generator.writeString("world");
            }
        };
    }

    public static JsonWriter forPolymorphicArray() {
        return new JsonObjectWriter() {
            @Override
            public void writeBody(JsonGenerator generator) throws IOException {
                generator.writeFieldName("BSON");

                generator.writeStartArray();

                generator.writeString("awesome");
                generator.writeString("");
                generator.writeString("awesome$*!()");
                generator.writeString("ελληνική");

                generator.writeNumber(5.05);
                generator.writeNumber(-7.19991);
                generator.writeNumber(Math.PI);

                generator.writeNull();

                generator.writeNumber(Integer.MAX_VALUE);
                generator.writeNumber(Integer.MIN_VALUE);
                generator.writeNumber(1986);

                generator.writeStartObject();
                generator.writeFieldName("name");
                generator.writeString("Gregory");
                generator.writeFieldName("age");
                generator.writeNumber(35);
                generator.writeEndObject();

                generator.writeNumber(Long.MAX_VALUE);
                generator.writeNumber(Long.MIN_VALUE);

                generator.writeStartArray();
                generator.writeNull();
                generator.writeBoolean(true);
                generator.writeBoolean(false);
                generator.writeEndArray();

                generator.writeBoolean(true);
                generator.writeBoolean(false);

                generator.writeEndArray();
            }
        };
    }


    public static JsonWriter forEmptyArray() {
        return new JsonObjectWriter() {
            @Override
            public void writeBody(JsonGenerator generator) throws IOException {
                generator.writeFieldName("example");
                generator.writeStartArray();
                generator.writeEndArray();
            }
        };
    }

    public static JsonWriter forLongArray(long start, int size) {
        long[] values = new long[size];
        for (int i = 0; i < size; i++) {
            values[i] = start + i;
        }
        return forLongArray(values);
    }

    public static JsonWriter forLongArray(final long[] values) {
        return new JsonObjectWriter() {
            @Override
            public void writeBody(JsonGenerator generator) throws IOException {
                generator.writeFieldName("example");
                generator.writeStartArray();
                for (int i = 0; i < values.length; i++) {
                    generator.writeNumber(values[i]);
                }
                generator.writeEndArray();
            }
        };
    }

    public static JsonWriter forIntegerArrayInterval(int from, int to) {
        int[] values = new int[to - from];
        for (int i = 0; i < values.length; i++) {
            values[i] = i + from;
        }
        return forIntegerArray(values);
    }

    public static JsonWriter forIntegerArray(final int[] values) {
        return new JsonObjectWriter() {
            @Override
            public void writeBody(JsonGenerator generator) throws IOException {
                generator.writeFieldName("example");
                generator.writeStartArray();
                for (int i = 0; i <= values.length; i++) {
                    generator.writeNumber(values[0]);
                }
                generator.writeEndArray();
            }
        };
    }

    public static JsonWriter forSimpleObject() {
        return new JsonObjectWriter() {
            @Override
            protected void writeBody(JsonGenerator generator) throws IOException {
                generator.writeArrayFieldStart("persons");
                for (String s : new String[] { "Karl", "Els", "Gregory", "John", "Mary", "Anton", "Elskse" }) {
                    writePerson(generator, s, s.hashCode());
                }
                generator.writeEndArray();
            }
        };
    }

    public static JsonWriter forPolymorphicObject() {
        return new JsonObjectWriter() {
            @Override
            protected void writeBody(JsonGenerator generator) throws IOException {
                generator.writeStringField("BigPolyMorphicObjectName", "big");
                generator.writeStringField("value1", "value1String");
                generator.writeStringField("value2", "value2String");
                generator.writeStringField("value3", "value3String");

                generator.writeNumberField("valueI64", 101201211121212L);
                generator.writeNumberField("valueI64Min", Long.MIN_VALUE);
                generator.writeNumberField("valueI64Max", Long.MAX_VALUE);
                generator.writeNumberField("valueI32", 100121);
                generator.writeNumberField("valueI32Max", Integer.MAX_VALUE);
                generator.writeNumberField("valueI32Min", Integer.MIN_VALUE);
                generator.writeNumberField("valueShort", (short) 123);
                generator.writeNumberField("valueDouble", 1.12332412001);
                generator.writeNumberField("valueDoublePI", Math.PI);
                generator.writeNumberField("valueFloat", 1.123f);

                generator.writeBooleanField("valueBoolean-True", true);
                generator.writeBooleanField("valueBoolean-False", false);

                generator.writeNullField("valueNull");

                generator.writeFieldName("valueSubObject1");
                writePerson(generator, "Els", 33);

                generator.writeFieldName("valueSubObject2");
                writePerson(generator, "John", 25);

                generator.writeFieldName("valueSubObject3");
                writePerson(generator, "Mary", 65);

                generator.writeFieldName("integerArray");
                forIntegerArrayInterval(-100, 100).write(generator);

                generator.writeFieldName("emptyArray");
                forEmptyArray().write(generator);

                generator.writeFieldName("longArray");
                forLongArray(1000000000000000L, 1000).write(generator);

                generator.writeFieldName("polyArray");
                forPolymorphicArray().write(generator);

                generator.writeArrayFieldStart("valueObjectArray");
                for (String s : new String[] { "Karl", "Els" }) {
                    writePerson(generator, s, s.hashCode());
                }
                generator.writeEndArray();
            }
        };
    }

    private static void writePerson(JsonGenerator generator, String name, int age) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("name", name);
        generator.writeNumberField("age", age);
        generator.writeEndObject();
    }
}
