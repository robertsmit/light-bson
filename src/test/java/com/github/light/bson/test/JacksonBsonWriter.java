package com.github.light.bson.test;

import com.fasterxml.jackson.core.JsonGenerator;
import com.github.light.bson.BsonWriter;
import org.codehaus.jackson.JsonFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;

/**
 * Created by rob on 12-10-14.
 */
public class JacksonBsonWriter implements BsonWriter {
    private JsonGenerator generator;

    public JacksonBsonWriter(JsonGenerator generator) {
        this.generator = generator;
    }

    @Override
    public BsonWriter field(String name) throws IOException {
        generator.writeFieldName(name);
        return this;
    }

    @Override
    public BsonWriter writeString(String value) throws IOException {
        generator.writeString(value);
        return this;
    }

    @Override
    public BsonWriter startArray() throws IOException {
        generator.writeStartArray();
        return this;
    }

    @Override
    public BsonWriter endArray() throws IOException {
        generator.writeEndArray();
        return this;
    }

    @Override
    public BsonWriter startObject() throws IOException {
        generator.writeStartObject();
        return this;
    }

    @Override
    public BsonWriter endObject() throws IOException {
        generator.writeEndObject();
        return this;
    }

    @Override
    public BsonWriter writeBoolean(boolean value) throws IOException {
        generator.writeBoolean(value);
        return this;
    }

    @Override
    public BsonWriter writeNull() throws IOException {
        generator.writeNull();
        return this;
    }

    @Override
    public BsonWriter writeNumber(short value) throws IOException {
        generator.writeNumber(value);
        return this;
    }

    @Override
    public BsonWriter writeNumber(double value) throws IOException {
        generator.writeNumber(value);
        return this;
    }

    @Override
    public BsonWriter writeNumber(int value) throws IOException {
        generator.writeNumber(value);
        return this;
    }

    @Override
    public BsonWriter writeNumber(long value) throws IOException {
        generator.writeNumber(value);
        return this;
    }

    @Override
    public BsonWriter writeStringField(String field, String value) throws IOException {
        generator.writeStringField(field, value);
        return this;
    }

    @Override
    public BsonWriter writeDatetime(long millis) throws IOException {
        throw new NotImplementedException();
    }
}
