package com.github.light.bson.util;

import java.util.Arrays;

/**
 * Created by rob on 18-10-14.
 */
public class GrowingByteBuffer extends GrowingBuffer {
    private byte[] buffer;

    public GrowingByteBuffer(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public GrowingByteBuffer(int capacity, float loadFactor) {
        super(loadFactor);
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        buffer = new byte[capacity];
    }

    public void put(byte v) {
        ensureRoom(1);
        unsafePut(v);
    }

    public byte pop() {
        return buffer[--position];
    }

    public byte peek() {
        return buffer[position - 1];
    }

    public void putAll(byte[] bytes) {
        ensureRoom(bytes.length);
        unsafePutAll(bytes, 0, bytes.length);
    }

    public byte[] toByteArray() {
        return Arrays.copyOf(buffer, position);
    }

    public void unsafePutAll(byte[] bytes, int offset, int length) {
        System.arraycopy(bytes, offset, buffer, position, length);
        position += length;
    }

    public void unsafePut(byte v) {
        unsafePut(position, v);
        position += 1;
    }

    private void unsafePut(int index, byte v) {
        buffer[index] = v;
    }

    @Override
    protected int capacity() {
        return buffer.length;
    }

    protected void setCapacity(int newCapacity) {
        buffer = Arrays.copyOf(buffer, newCapacity);
    }
}
