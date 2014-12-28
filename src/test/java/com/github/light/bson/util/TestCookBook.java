package com.github.light.bson.util;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;

/**
 * Created by rob on 21-12-14.
 */
public class TestCookBook {
    private TestCookBook() {
    }

    public static JsonRecipe helloWorld() {
        return new ObjectJsonRecipe() {
            @Override
            public void writeBody(JsonGenerator generator) throws IOException {
                generator.writeFieldName("hello");
                generator.writeString("world");
            }
        };
    }

    public static JsonRecipe binaryData(final byte[] data) {
        return new ObjectJsonRecipe() {
            @Override
            public void writeBody(JsonGenerator generator) throws IOException {
                generator.writeFieldName("data");
                generator.writeBinary(data);
            }
        };
    }

    public static JsonRecipe polymorphicArray() {
        return new ObjectJsonRecipe() {
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


    public static JsonRecipe emptyArray() {
        return new ObjectJsonRecipe() {
            @Override
            public void writeBody(JsonGenerator generator) throws IOException {
                generator.writeFieldName("example");
                generator.writeStartArray();
                generator.writeEndArray();
            }
        };
    }

    public static JsonRecipe longArray(long start, int size) {
        long[] values = new long[size];
        for (int i = 0; i < size; i++) {
            values[i] = start + i;
        }
        return longArray(values);
    }

    public static JsonRecipe longArray(final long[] values) {
        return new ObjectJsonRecipe() {
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

    public static JsonRecipe integerArrayInterval(int from, int to) {
        int[] values = new int[to - from];
        for (int i = 0; i < values.length; i++) {
            values[i] = i + from;
        }
        return integerArray(values);
    }

    public static JsonRecipe integerArray(final int[] values) {
        return new ObjectJsonRecipe() {
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

    public static JsonRecipe simpleObject() {
        return new ObjectJsonRecipe() {
            @Override
            protected void writeBody(JsonGenerator generator) throws IOException {
                generator.writeArrayFieldStart("persons");
                for (String s : new String[] { "Karl", "Els", "Gregory", "John", "Mary", "Anton", "Elskse" }) {
                    person(s, s.hashCode()).write(generator);
                }
                generator.writeEndArray();
            }
        };
    }

    public static JsonRecipe polymorphicObject() {
        return new ObjectJsonRecipe() {
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
                person("Els", 33).write(generator);

                generator.writeFieldName("valueSubObject2");
                person("John", 25).write(generator);

                generator.writeFieldName("valueSubObject3");
                person("Mary", 65).write(generator);

                generator.writeFieldName("integerArray");
                integerArrayInterval(-100, 100).write(generator);

                generator.writeFieldName("emptyArray");
                emptyArray().write(generator);

                generator.writeFieldName("longArray");
                longArray(1000000000000000L, 1000).write(generator);

                generator.writeFieldName("polyArray");
                polymorphicArray().write(generator);

                generator.writeArrayFieldStart("valueObjectArray");
                for (String s : new String[] { "Karl", "Els" }) {
                    person(s, s.hashCode()).write(generator);
                }
                generator.writeEndArray();
            }
        };
    }

    public static JsonRecipe person(final String name, final int age) throws IOException {
        return new ObjectJsonRecipe() {

            @Override
            protected void writeBody(JsonGenerator generator) throws IOException {
                generator.writeStringField("name", name);
                generator.writeNumberField("age", age);
            }
        };
    }
}
