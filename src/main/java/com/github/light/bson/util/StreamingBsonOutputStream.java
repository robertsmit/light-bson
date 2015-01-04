package com.github.light.bson.util;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by rob on 4-1-15.
 */
public class StreamingBsonOutputStream extends AbstractBsonOutputStream {
    private OutputStream out;

    public StreamingBsonOutputStream(OutputStream out, FieldCache fieldCache) {
        super(fieldCache);
        this.out = out;
    }

    @Override
    public void close() throws IOException {
        out.close();
    }

    @Override
    public int startDocument() throws IOException {
        writeInt(0);
        return 0;
    }

    @Override
    protected void onWrite(int num) {

    }

    @Override
    protected void write(byte[] bytes, int offset, int length) throws IOException {
        out.write(bytes, offset, length);
    }

    @Override
    protected void write(byte b) throws IOException {
        out.write(b);
    }
}
