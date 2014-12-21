package com.github.light.bson.util;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.GenericArrayType;

/**
 * Created by rob on 21-12-14.
 */
public abstract class JsonObjectWriter implements JsonWriter {
    @Override
    public void write(JsonGenerator generator) throws IOException {
        generator.writeStartObject();
        writeBody(generator);
        generator.writeEndObject();
    }

    protected abstract void writeBody(JsonGenerator generator) throws IOException;
}
