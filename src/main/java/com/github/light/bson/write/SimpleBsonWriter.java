package com.github.light.bson.write;

import com.github.light.bson.BsonWriter;

import java.io.IOException;

/**
 * Created by rob on 12-10-14.
 */
public class SimpleBsonWriter implements BsonWriter {
    private BsonWriteState state;

    public SimpleBsonWriter(BsonBuffer out) {
        state = new StartBsonWriteState(out);
    }

    @Override
    public BsonWriter field(String name) {
        state = state.field(name);
        return this;
    }

    @Override
    public BsonWriter writeNumber(double value) throws IOException {
        state = state.writeNumber(value);
        return this;
    }

    @Override
    public BsonWriter writeString(String value) throws IOException {
        state = state.writeString(value);
        return this;
    }

    @Override
    public BsonWriter startArray() throws IOException {
        state = state.startArray();
        return this;
    }

    @Override
    public BsonWriter endArray() throws IOException {
        state = state.endArray();
        return this;
    }

    @Override
    public BsonWriter startObject() throws IOException {
        state = state.startObject();
        return this;
    }

    @Override
    public BsonWriter endObject() throws IOException {
        state = state.endObject();
        return this;
    }

    @Override
    public BsonWriter writeBoolean(boolean value) throws IOException {
        state = state.writeBoolean(value);
        return this;
    }

    @Override
    public BsonWriter writeNull() throws IOException {
        state = state.writeNull();
        return this;
    }

    @Override
    public BsonWriter writeNumber(short value) throws IOException {
        state = state.writeNumber(value);
        return this;
    }

    @Override
    public BsonWriter writeNumber(int value) throws IOException {
        state = state.writeNumber(value);
        return this;
    }

    @Override
    public BsonWriter writeNumber(long value) throws IOException {
        state = state.writeNumber(value);
        return this;
    }

    @Override
    public BsonWriter writeDatetime(long millis) throws IOException {
        state = state.writeDatetime(millis);
        return this;
    }
}
