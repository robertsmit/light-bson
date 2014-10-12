package com.github.light.bson.write;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by rob on 12-10-14.
 */
public class BsonBuffer {
    public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private byte[] buffer;
    private int position;

    public BsonBuffer(int size) {
        if (size < 0) {
            throw new IllegalArgumentException();
        }
        buffer = new byte[size];
    }

    public int position() {
        return position;
    }

    public void position(int position) {
        if (position < 0) {
            throw new IllegalArgumentException();
        }
        this.position = position;
    }

    public BsonBuffer putDouble(double value) throws IOException {
        return putLong(Double.doubleToRawLongBits(value));
    }

    public BsonBuffer putField(String name, byte type) throws IOException {
        byte[] content = name.getBytes(UTF8_CHARSET);
        return putField(content, type);
    }

    protected BsonBuffer putField(byte[] name, byte type) throws IOException {
        ensureRoom(name.length + 2);
        put(type);
        put(name);
        put(BsonConstants.TERMINATOR);
        return this;
    }

    public BsonBuffer putString(String v) throws IOException {
        byte[] content = v.getBytes(UTF8_CHARSET);
        int size = content.length + 1;
        putInt(size);
        ensureRoom(size + 1);
        put(content);
        put(BsonConstants.TERMINATOR);
        return this;
    }

    public BsonBuffer putByte(byte v) throws IOException {
        putSafe(v);
        return this;
    }

    public BsonBuffer putInt(int index, int v) throws IOException {
        if (index + 4 > position) {
            throw new IllegalArgumentException();
        }
        int tempPosition = index;
        put(tempPosition++, (byte) v);
        put(tempPosition++, (byte) (v >> 8));
        put(tempPosition++, (byte) (v >> 16));
        put(tempPosition, (byte) (v >> 24));
        return this;
    }

    public BsonBuffer putInt(int v) throws IOException {
        ensureRoom(4);
        put((byte) v);
        put((byte) (v >> 8));
        put((byte) (v >> 16));
        put((byte) (v >> 24));
        return this;
    }

    public BsonBuffer putLong(long v) throws IOException {
        ensureRoom(8);
        put((byte) v);
        put((byte) (v >> 8));
        put((byte) (v >> 16));
        put((byte) (v >> 24));
        put((byte) (v >> 32));
        put((byte) (v >> 40));
        put((byte) (v >> 48));
        put((byte) (v >> 56));
        return this;
    }

    public byte[] toByteArray() {
        return Arrays.copyOf(buffer, position);
    }

    private void put(byte v) {
        put(position, v);
        position += 1;
    }

    private void putSafe(byte v) {
        ensureCapacity(position + 1);
        put(v);
    }

    private void put(int index, byte v) {
        buffer[index] = v;
    }

    private void putSafe(byte[] bytes) throws IOException {
        ensureCapacity(position + bytes.length);
        put(bytes);
    }

    private void put(byte[] bytes) throws IOException {
        System.arraycopy(bytes, 0, buffer, position, bytes.length);
        position += bytes.length;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - buffer.length > 0) {
            grow(minCapacity);
        }
    }

    private void ensureRoom(int room) {
        ensureCapacity(position + room);
    }

    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = buffer.length;
        int newCapacity = oldCapacity << 1;
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity < 0) {
            if (minCapacity < 0) {
                throw new OutOfMemoryError();
            }
            newCapacity = Integer.MAX_VALUE;
        }
        buffer = Arrays.copyOf(buffer, newCapacity);
    }
}
