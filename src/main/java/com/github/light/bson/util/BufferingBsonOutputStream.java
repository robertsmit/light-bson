package com.github.light.bson.util;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by rob on 12-10-14.
 */
public class BufferingBsonOutputStream extends AbstractBsonOutputStream {
    public static final int DEFAULT_INITIAL_BUFFER_SIZE = 1024;
    private GrowingByteBuffer buffer;
    private OutputStream out;

    public BufferingBsonOutputStream(OutputStream out) {
        this(out, new FieldCache());
    }

    public BufferingBsonOutputStream(OutputStream out, FieldCache cache) {
        this(out, DEFAULT_INITIAL_BUFFER_SIZE, cache);
    }

    public BufferingBsonOutputStream(OutputStream out, int initialBufferSize, FieldCache fieldCache) {
        this(out, new GrowingByteBuffer(initialBufferSize), fieldCache);
    }

    public BufferingBsonOutputStream(OutputStream out, GrowingByteBuffer buffer, FieldCache fieldCache) {
        super(fieldCache);
        this.out = out;
        this.buffer = buffer;
        buffer.reset();
    }

    @Override
    public int startDocument() throws IOException {
        int mark = buffer.position();
        buffer.position(mark + 4);
        return mark;
    }

    @Override
    public void endDocument(int mark) throws IOException {
        super.endDocument(mark);
        int position = buffer.position();
        buffer.position(mark);
        writeInt(position - mark);
        buffer.position(position);
    }

    @Override
    public void close() throws IOException {
        out.write(buffer.toByteArray());
    }

    protected void write(byte b) {
        buffer.unsafePut(b);
    }

    protected void write(byte[] b, int offset, int length) {
        buffer.unsafePutAll(b, offset, length);
    }

    protected void onWrite(int num) {
        buffer.ensureRoom(num);
    }
}
