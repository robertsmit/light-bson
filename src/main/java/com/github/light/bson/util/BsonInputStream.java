package com.github.light.bson.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rob on 17-12-14.
 *
 * Not thread safe because of the sharedReadBuffer!
 */
public class BsonInputStream {
    private InputStream stream;
    private FieldCache fieldCache;
    private GrowingByteBuffer sharedReadBuffer = new GrowingByteBuffer(64);

    public BsonInputStream(InputStream stream) {
        this(stream, new FieldCache());
    }

    public BsonInputStream(InputStream stream, FieldCache fieldCache) {
        this.stream = stream;
        this.fieldCache = fieldCache;
    }

    public void close() throws IOException {
        stream.close();
    }

    public boolean readBoolean() throws IOException {
        byte boolByte = readByte();
        return boolByte > 0;
    }

    public byte readByte() throws IOException {
        int read = stream.read();
        DataInputStream s;
        if (read == -1) {
            throw new IOException();
        }
        return (byte) read;
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    public byte[] readFieldBytes() throws IOException {
        sharedReadBuffer.reset();
        byte read;
        while ((read = readByte()) > 0) {
            sharedReadBuffer.put(read);
        }
        if (sharedReadBuffer.position() == 0) {
            throw new IOException();
        }
        return sharedReadBuffer.toByteArray();
    }

    public String readFieldString() throws IOException {
        byte[] encodedField = readFieldBytes();
        return fieldCache.getDecodedField(encodedField);
    }

    public int readInt() throws IOException {
        byte[] bytes = read(4);
        return bytes[0] & 0xff
                | (bytes[1] & 0xff) << 8
                | (bytes[2] & 0xff) << 16
                | (bytes[3]) << 24;
    }

    public long readLong() throws IOException {
        byte[] bytes = read(8);
        return ((((long) bytes[7]) << 56) |
                (((long) bytes[6] & 0xff) << 48) |
                (((long) bytes[5] & 0xff) << 40) |
                (((long) bytes[4] & 0xff) << 32) |
                (((long) bytes[3] & 0xff) << 24) |
                (((long) bytes[2] & 0xff) << 16) |
                (((long) bytes[1] & 0xff) << 8) |
                (((long) bytes[0] & 0xff)));
    }

    public void readNull() throws IOException {
        byte read = readByte();
        if (read != 0) {
            throw new IllegalStateException("expected null");
        }
    }

    public String readString() throws IOException {
        byte[] stringBytes = readStringBytes();
        return new String(stringBytes, BsonConstants.UTF8_CHARSET);
    }

    public byte[] readStringBytes() throws IOException {
        int length = readInt();
        byte[] stringBytes = read(length - 1);
        readNull();
        return stringBytes;
    }

    public void skip(long i) throws IOException {
        stream.skip(i);
    }

    private byte[] read(int length) throws IOException {
        byte[] buffer = new byte[length];
        read(buffer);
        return buffer;
    }

    private void read(byte[] buffer) throws IOException {
        int totalRead = 0;
        while (totalRead < buffer.length) {
            int read = stream.read(buffer, totalRead, buffer.length - totalRead);
            if (read == -1) {
                throw new IOException();
            }
            totalRead += read;
        }
    }
}
