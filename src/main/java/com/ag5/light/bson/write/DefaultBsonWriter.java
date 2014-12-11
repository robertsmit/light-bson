package com.ag5.light.bson.write;

import com.ag5.light.bson.BsonWriter;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by rob on 12-10-14.
 */
public class DefaultBsonWriter implements BsonWriter {
    private Cache cache;
    private WriteContext context;

    public DefaultBsonWriter(OutputStream out, Cache cache) {
        this(new BsonStream(out), cache);
    }

    public DefaultBsonWriter(BsonStream out, Cache cache) {
        context = new StartWriteContext(out);
        this.cache = cache;
    }

    @Override
    public BsonWriter writeField(String field) throws IOException {
        byte[] encodedField = cache.getEncodeField(field);
        context = context.writeField(encodedField);
        return this;
    }

    @Override
    public BsonWriter writeNumber(double value) throws IOException {
        context = context.writeDouble(value);
        return this;
    }

    @Override
    public BsonWriter writeString(String value) throws IOException {
        context = context.writeString(value);
        return this;
    }

    @Override
    public BsonWriter writeStartArray() throws IOException {
        context = context.writeStartArray();
        return this;
    }

    @Override
    public BsonWriter writeEndArray() throws IOException {
        context = context.writeEndArray();
        return this;
    }

    @Override
    public BsonWriter writeStartObject() throws IOException {
        context = context.writeStartObject();
        return this;
    }

    @Override
    public BsonWriter writeEndObject() throws IOException {
        context = context.writeEndObject();
        return this;
    }

    @Override
    public BsonWriter writeBoolean(boolean value) throws IOException {
        context = context.writeBoolean(value);
        return this;
    }

    @Override
    public BsonWriter writeNull() throws IOException {
        context = context.writeNull();
        return this;
    }

    @Override
    public BsonWriter writeNumber(short value) throws IOException {
        return writeNumber((int)value);
    }

    @Override
    public BsonWriter writeNumber(int value) throws IOException {
        context = context.writeInt(value);
        return this;
    }

    @Override
    public BsonWriter writeNumber(long value) throws IOException {
        context = context.writeLong(value);
        return this;
    }

    @Override
    public BsonWriter writeDatetime(long millis) throws IOException {
        context = context.writeDatetime(millis);
        return this;
    }

    @Override
    public void close() throws IOException {
        context.getOut().close();
    }
}
