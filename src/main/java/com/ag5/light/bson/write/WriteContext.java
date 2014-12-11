package com.ag5.light.bson.write;

import java.io.IOException;

/**
 * Created by rob on 20-10-14.
 */
public abstract class WriteContext {
    public abstract BsonStream getOut();

    public WriteContext writeField(byte[] name) throws IOException {
        throw new IllegalStateException();
    }

    public WriteContext writeString(String value) throws IOException {
        throw new IllegalStateException();
    }

    public WriteContext writeStartArray() throws IOException {
        throw new IllegalStateException();
    }

    public WriteContext writeEndArray() throws IOException {
        throw new IllegalStateException();
    }

    public WriteContext writeStartObject() throws IOException {
        int mark = getOut().startDocument();
        return new ObjectWriteContext(this, mark);
    }

    public WriteContext writeEndObject() throws IOException {
        throw new IllegalStateException();
    }

    public WriteContext writeBoolean(boolean value) throws IOException {
        throw new IllegalStateException();
    }

    public WriteContext writeNull() throws IOException {
        throw new IllegalStateException();
    }

    public WriteContext writeInt(int value) throws IOException {
        throw new IllegalStateException();
    }

    public WriteContext writeLong(long value) throws IOException {
        throw new IllegalStateException();
    }

    public WriteContext writeDouble(double value) throws IOException {
        throw new IllegalStateException();
    }

    public WriteContext writeDatetime(long millis) throws IOException {
        throw new IllegalStateException();
    }
}
