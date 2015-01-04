package com.github.light.bson.util;

import java.io.IOException;

/**
 * Created by rob on 4-1-15.
 */
public abstract class AbstractBsonOutputStream implements BsonOutputStream {
    protected FieldCache fieldCache;

    public AbstractBsonOutputStream(FieldCache fieldCache) {
        this.fieldCache = fieldCache;
    }

    @Override
    public void endDocument(int mark) throws IOException {
        onWrite(1);
        write(BsonConstants.TERMINATOR);
    }

    @Override
    public void writeInt(int v) throws IOException {
        onWrite(4);
        write(extractByte(v, 0));
        write(extractByte(v, 1));
        write(extractByte(v, 2));
        write(extractByte(v, 3));
    }

    @Override
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

    protected abstract void onWrite(int num);

    @Override
    public void writeDouble(double value) throws IOException {
        writeLong(Double.doubleToRawLongBits(value));
    }

    @Override
    public void writeBoolean(boolean value) throws IOException {
        onWrite(1);
        write(value ? BsonConstants.TRUE : BsonConstants.FALSE);
    }

    @Override
    public void writeBinary(byte subtype, byte[] bytes, int offset, int length) throws IOException {
        onWrite(4 + 1 + length);
        writeInt(length);
        write(subtype);
        write(bytes, offset, length);
    }

    protected abstract void write(byte[] bytes, int offset, int length) throws IOException;

    @Override
    public void writeString(String v) throws IOException {
        byte[] content = v.getBytes(BsonConstants.UTF8_CHARSET);
        writeString(content);
    }

    @Override
    public void writeString(byte[] content) throws IOException {
        int size = content.length + 1;
        writeInt(size);
        onWrite(size);
        write(content, 0, content.length);
        write(BsonConstants.TERMINATOR);
    }

    @Override
    public void writeField(byte[] encodedField, byte type) throws IOException {
        int length = encodedField.length;
        onWrite(1 + length + 1);
        write(type);
        write(encodedField, 0, length);
        write(BsonConstants.TERMINATOR);
    }

    @Override
    public void writeField(String name, byte type) throws IOException {
        byte[] encodedField = fieldCache.getEncodeField(name);
        writeField(encodedField, type);
    }

    protected abstract void write(byte b) throws IOException;

    private byte extractByte(int v, int position) {
        return (byte)((v >> position * 8));
    }

    private byte extractByte(long v, int position) {
        return (byte)((v >> position * 8));
    }
}
