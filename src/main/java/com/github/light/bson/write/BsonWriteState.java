package com.github.light.bson.write;

import com.github.light.bson.BsonWriter;

import java.io.IOException;

/**
 * Created by rob on 12-10-14.
 */
 class BsonWriteState implements BsonWriter {
    protected BsonWriteState previousState;
    protected BsonBuffer buffer;

    protected BsonWriteState(BsonBuffer buffer) {
        this.buffer = buffer;
    }

    protected BsonWriteState(BsonWriteState previousState) {
        this.previousState = previousState;
        buffer = previousState.buffer;
    }

    @Override
    public BsonWriteState field(String name) {
        throw new IllegalStateException();
    }

    @Override
    public BsonWriteState writeString(String value) throws IOException {
        throw new IllegalStateException();
    }

    @Override
    public BsonWriteState startArray() throws IOException {
        throw new IllegalStateException();
    }

    @Override
    public BsonWriteState endArray() throws IOException {
        throw new IllegalStateException();
    }

    @Override
    public BsonWriteState startObject() throws IOException {
        throw new IllegalStateException();
    }

    @Override
    public BsonWriteState endObject() throws IOException {
        throw new IllegalStateException();
    }

    @Override
    public BsonWriteState writeBoolean(boolean value) throws IOException {
        throw new IllegalStateException();
    }

    @Override
    public BsonWriteState writeNull() throws IOException {
        throw new IllegalStateException();
    }

    @Override
    public BsonWriteState writeNumber(double value) throws IOException {
        throw new IllegalStateException();
    }

    @Override
    public BsonWriteState writeNumber(short value) throws IOException {
        throw new IllegalStateException();
    }

    @Override
    public BsonWriteState writeNumber(int value) throws IOException {
        throw new IllegalStateException();
    }

    @Override
    public BsonWriteState writeNumber(long value) throws IOException {
        throw new IllegalStateException();
    }

    @Override
    public BsonWriteState writeDatetime(long millis) throws IOException {
        throw new IllegalStateException();
    }

    @Override
    public BsonWriteState writeStringField(String field, String value) throws IOException {
        throw new IllegalStateException();
    }
}
