package com.github.light.bson.generator;

import com.github.light.bson.util.BsonConstants;
import com.github.light.bson.util.BsonOutputStream;
import com.github.light.bson.generator.state.StartWriteState;
import com.github.light.bson.generator.state.WriteState;

import java.io.IOException;
import java.util.Date;

/**
 * Created by rob on 12-10-14.
 */
public class DefaultBsonGenerator implements BsonGenerator {
    private WriteState state;
    private BsonOutputStream out;

    public DefaultBsonGenerator(BsonOutputStream out) {
        state = new StartWriteState(out);
        this.out = out;
    }

    @Override
    public BsonGenerator writeField(String field) throws IOException {
        state = state.writeField(field, out);
        return this;
    }

    @Override
    public BsonGenerator writeNumber(double value) throws IOException {
        state = state.writeDouble(value, out);
        return this;
    }

    @Override
    public BsonGenerator writeString(String value) throws IOException {
        state = state.writeString(value, out);
        return this;
    }

    @Override
    public BsonGenerator writeStartArray() throws IOException {
        state = state.writeStartArray(out);
        return this;
    }

    @Override
    public BsonGenerator writeEndArray() throws IOException {
        state = state.writeEndArray(out);
        return this;
    }

    @Override
    public BsonGenerator writeStartObject() throws IOException {
        state = state.writeStartObject(out);
        return this;
    }

    @Override
    public BsonGenerator writeEndObject() throws IOException {
        state = state.writeEndObject(out);
        return this;
    }

    @Override
    public BsonGenerator writeBoolean(boolean value) throws IOException {
        state = state.writeBoolean(value, out);
        return this;
    }

    @Override
    public BsonGenerator writeNull() throws IOException {
        state = state.writeNull(out);
        return this;
    }

    @Override
    public BsonGenerator writeNumber(short value) throws IOException {
        return writeNumber((int)value);
    }

    @Override
    public BsonGenerator writeNumber(int value) throws IOException {
        state = state.writeInt(value, out);
        return this;
    }

    @Override
    public BsonGenerator writeNumber(long value) throws IOException {
        state = state.writeLong(value, out);
        return this;
    }

    @Override
    public BsonGenerator writeDatetime(long millis) throws IOException {
        state = state.writeDatetime(millis, out);
        return this;
    }

    @Override
    public BsonGenerator writeDatetime(Date date) throws IOException {
        long millis = date.getTime();
        return writeDatetime(millis);
    }

    @Override
    public BsonGenerator writeBinary(byte subtype, byte[] value, int offset, int length) throws IOException {
        state = state.writeBinary(out, subtype, value, offset, length);
        return this;
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
