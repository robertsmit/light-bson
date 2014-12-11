package com.ag5.light.bson.write;

import com.ag5.light.bson.util.GrowingByteBuffer;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by rob on 12-10-14.
 */
public class BsonStream implements Closeable {
    protected GrowingByteBuffer buffer;
    protected OutputStream out;

    public BsonStream(OutputStream out) {
        this(out, 2048);
    }

    public BsonStream(OutputStream out, int size) {
        this(out, new GrowingByteBuffer(size));
    }

    public BsonStream(OutputStream out, GrowingByteBuffer buffer) {
        this.out = out;
        this.buffer = buffer;
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
        write((byte) v);
        write((byte) (v >> 8));
        write((byte) (v >> 16));
        write((byte) (v >> 24));
    }

    public void writeLong(long v) throws IOException {
        onWrite(8);
        write((byte) v);
        write((byte) (v >> 8));
        write((byte) (v >> 16));
        write((byte) (v >> 24));
        write((byte) (v >> 32));
        write((byte) (v >> 40));
        write((byte) (v >> 48));
        write((byte) (v >> 56));
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

    private void write(byte b) {
        buffer.putUnsafe(b);
    }

    private void write(byte[] b) {
        buffer.putUnsafe(b);
    }

    @Override
    public void close() throws IOException {
        out.write(buffer.toByteArray());
    }

    public void writeField(byte[] name, byte type) throws IOException {
        onWrite(name.length + 2);
        write(type);
        write(name);
        write(BsonConstants.TERMINATOR);
    }

    private void onWrite(int size) {
        buffer.ensureRoom(size);
    }
}
