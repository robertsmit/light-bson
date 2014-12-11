package com.ag5.light.bson.util;

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
        putUnsafe(v);
    }

    public byte pop() {
        return buffer[--position];
    }

    public byte peek() {
        return buffer[position - 1];
    }

    public void put(byte[] bytes) {
        ensureRoom(bytes.length);
        putUnsafe(bytes);
    }

    public byte[] toByteArray() {
        return Arrays.copyOf(buffer, position);
    }

    public void putUnsafe(byte[] bytes) {
        System.arraycopy(bytes, 0, buffer, position, bytes.length);
        position += bytes.length;
    }

    public void put(int index, byte v) {
        buffer[index] = v;
    }

    public void putUnsafe(byte v) {
        put(position, v);
        position += 1;
    }

    @Override
    protected int capacity() {
        return buffer.length;
    }

    protected void capacity(int newCapacity) {
        buffer = Arrays.copyOf(buffer, newCapacity);
    }
}
