package com.github.light.bson.write;

import java.io.IOException;

/**
 * Created by rob on 20-10-14.
 */
public class ArrayWriteContext extends InnerWriteContext {
    private static byte[][] INDEX_FIELDS;
    private int index = 0;

    public ArrayWriteContext(WriteContext parent, int mark) {
        super(parent, mark);
    }

    @Override
    public WriteContext writeEndArray() throws IOException {
        return end();
    }

    @Override
    protected byte[] popField() {
        return getField(index++);
    }

    private byte[] getField(int index) {
        if (INDEX_FIELDS == null) {
            initializeIndexField();
        }
        if (index < INDEX_FIELDS.length) {
            return INDEX_FIELDS[index];
        }
        else {
            return encodeField(index);
        }
    }

    private byte[] encodeField(int index) {
        return Integer.toString(index).getBytes(BsonConstants.UTF8_CHARSET);
    }

    private void initializeIndexField() {
        INDEX_FIELDS = new byte[200][];
        for (int i = 0; i < INDEX_FIELDS.length; i++) {
            INDEX_FIELDS[i] = encodeField(i);
        }
    }
}
