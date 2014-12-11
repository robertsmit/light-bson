package com.github.light.bson.util;

/**
 * Created by rob on 18-10-14.
 */
public abstract class GrowingBuffer {
    protected static final float DEFAULT_LOAD_FACTOR = 0.75f;
    protected int position;
    private float loadFactor;

    protected GrowingBuffer(float loadFactor) {
        if (loadFactor <= 0.0f) {
            throw new IllegalArgumentException();
        }
        this.loadFactor = loadFactor;
        position = 0;
    }

    public void reset() {
        position = 0;
    }

    public int position() {
        return position;
    }

    public void position(int position) {
        if (position < 0) {
            throw new IllegalArgumentException();
        }
        ensureCapacity(position + 1);
        this.position = position;
    }

    public void ensureRoom(int room) {
        ensureCapacity(position + room);
    }

    protected void ensureCapacity(int minCapacity) {
        if (capacity() < minCapacity) {
            grow(minCapacity);
        }
    }

    protected abstract void capacity(int newCapacity);

    abstract protected int capacity();

    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = capacity();
        int newCapacity = oldCapacity + (int) Math.ceil(oldCapacity * loadFactor);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity < 0) {
            if (minCapacity < 0) {
                throw new OutOfMemoryError();
            }
            newCapacity = Integer.MAX_VALUE;
        }
        capacity(newCapacity);
    }
}
