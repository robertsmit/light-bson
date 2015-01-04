package com.github.light.bson.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by rob on 4-1-15.
 */
public interface BsonOutputStream extends Closeable {
    int startDocument() throws IOException;

    void endDocument(int mark) throws IOException;

    void writeField(String name, byte type) throws IOException;

    void writeBinary(byte subtype, byte[] bytes, int offset, int length) throws IOException;

    void writeBoolean(boolean value) throws IOException;

    void writeDouble(double value) throws IOException;

    void writeField(byte[] encodedField, byte type) throws IOException;

    void writeInt(int v) throws IOException;

    void writeLong(long v) throws IOException;

    void writeString(String v) throws IOException;

    void writeString(byte[] content) throws IOException;
}
