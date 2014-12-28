package com.github.light.bson.generator.state;

import com.github.light.bson.util.BsonOutputStream;

import java.io.IOException;

/**
 * Created by rob on 20-10-14.
 */
public abstract class WriteState {
    public WriteState writeBinary(BsonOutputStream out, byte subtype, byte[] value, int offset, int length) throws IOException {
        throw new IllegalStateException();
    }

    public WriteState writeField(String name, BsonOutputStream out) throws IOException {
        throw new IllegalStateException();
    }

    public WriteState writeString(String value, BsonOutputStream out) throws IOException {
        throw new IllegalStateException();
    }

    public WriteState writeStartArray(BsonOutputStream out) throws IOException {
        throw new IllegalStateException();
    }

    public WriteState writeEndArray(BsonOutputStream out) throws IOException {
        throw new IllegalStateException();
    }

    public WriteState writeStartObject(BsonOutputStream out) throws IOException {
        int mark = out.startDocument();
        return new ObjectWriteState(this, mark);
    }

    public WriteState writeEndObject(BsonOutputStream out) throws IOException {
        throw new IllegalStateException();
    }

    public WriteState writeBoolean(boolean value, BsonOutputStream out) throws IOException {
        throw new IllegalStateException();
    }

    public WriteState writeNull(BsonOutputStream out) throws IOException {
        throw new IllegalStateException();
    }

    public WriteState writeInt(int value, BsonOutputStream out) throws IOException {
        throw new IllegalStateException();
    }

    public WriteState writeLong(long value, BsonOutputStream out) throws IOException {
        throw new IllegalStateException();
    }

    public WriteState writeDouble(double value, BsonOutputStream out) throws IOException {
        throw new IllegalStateException();
    }

    public WriteState writeDatetime(long millis, BsonOutputStream out) throws IOException {
        throw new IllegalStateException();
    }
}
