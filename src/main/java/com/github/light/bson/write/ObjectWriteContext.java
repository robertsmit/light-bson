package com.github.light.bson.write;

import java.io.IOException;

/**
 * Created by rob on 20-10-14.
 */
public class ObjectWriteContext extends InnerWriteContext {
    private byte[] field;

    public ObjectWriteContext(WriteContext parent, int mark) {
        super(parent, mark);
    }

    @Override
    public WriteContext writeEndObject() throws IOException {
        return end();
    }

    @Override
    public WriteContext writeField(byte[] name) throws IOException {
        if (field != null) {
            throw new IllegalStateException();
        }
        field = name;
        return this;
    }

    @Override
    protected byte[] popField() {
        if (field == null) {
            throw new IllegalStateException();
        }
        byte[] f = field;
        field = null;
        return f;
    }
}
