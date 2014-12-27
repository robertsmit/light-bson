package com.github.light.bson.util;

import com.github.light.bson.util.BsonConstants;
import com.github.light.bson.util.GrowingByteBuffer;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by rob on 12-10-14.
 */
public class BsonOutputStream implements Closeable {
    public static final int DEFAULT_INITIAL_BUFFER_SIZE = 2000;
    private GrowingByteBuffer buffer;
    private FieldCache fieldCache;
    private OutputStream out;

    public BsonOutputStream(OutputStream out) {
        this(out, 2000, new FieldCache());
    }

    public BsonOutputStream(OutputStream out, FieldCache cache) {
        this(out, DEFAULT_INITIAL_BUFFER_SIZE, cache);
    }

    public BsonOutputStream(OutputStream out, int initialBufferSize, FieldCache fieldCache) {
        this(out, new GrowingByteBuffer(initialBufferSize), fieldCache);
    }

    public BsonOutputStream(OutputStream out, GrowingByteBuffer buffer, FieldCache fieldCache) {
        this.out = out;
        this.buffer = buffer;
        this.fieldCache = fieldCache;
        buffer.reset();
    }

    public int startDocument() throws IOException {
        int mark = buffer.position();
        buffer.position(mark + 4);
        return mark;
    }

    public void endDocument(int mark) throws IOException {
        onWrite(1);
        write(BsonConstants.TERMINATOR);
        int position = buffer.position();
        buffer.position(mark);
        writeInt(position - mark);
        buffer.position(position);
    }

    public void writeBoolean(boolean value) throws IOException {
        onWrite(1);
        write(value ? BsonConstants.TRUE : BsonConstants.FALSE);
    }

    public void writeDouble(double value) throws IOException {
        writeLong(Double.doubleToRawLongBits(value));
    }

    public void writeInt(int v) throws IOException {
        onWrite(4);
        write(extractByte(v, 0));
        write(extractByte(v, 1));
        write(extractByte(v, 2));
        write(extractByte(v, 3));
    }

    public void writeLong(long v) throws IOException {
        onWrite(8);
        write(extractByte(v, 0));
        write(extractByte(v, 1));
        write(extractByte(v, 2));
        write(extractByte(v, 3));
        write(extractByte(v, 4));
        write(extractByte(v, 5));
        write(extractByte(v, 6));
        write(extractByte(v, 7));
    }

    public void writeString(String v) throws IOException {
        byte[] content = v.getBytes(BsonConstants.UTF8_CHARSET);
        writeString(content);
    }

    public void writeString(byte[] content) throws IOException {
        int size = content.length + 1;
        writeInt(size);
        onWrite(size);
        write(content);
        write(BsonConstants.TERMINATOR);
    }

    @Override
    public void close() throws IOException {
        out.write(buffer.toByteArray());
    }

    public void writeField(String name, byte type) throws IOException {
        byte[] encodedField = fieldCache.getEncodeField(name);
        writeField(encodedField, type);
    }

    public void writeField(byte[] encodedField, byte type) throws IOException {
        onWrite(encodedField.length + 2);
        write(type);
        write(encodedField);
        write(BsonConstants.TERMINATOR);
    }

    private byte extractByte(int v, int position) {
        return (byte)((v >> position * 8));
    }

    private byte extractByte(long v, int position) {
        return (byte)((v >> position * 8));
    }

    private void write(byte b) {
        buffer.putUnsafe(b);
    }

    private void write(byte[] b) {
        buffer.putUnsafe(b);
    }

    private void onWrite(int size) {
        buffer.ensureRoom(size);
    }
}
