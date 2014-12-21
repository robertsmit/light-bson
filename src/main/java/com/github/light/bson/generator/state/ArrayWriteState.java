package com.github.light.bson.generator.state;

import com.github.light.bson.util.BsonOutputStream;
import com.github.light.bson.util.BsonConstants;

import java.io.IOException;

/**
 * Created by rob on 20-10-14.
 */
public class ArrayWriteState extends InnerWriteState {
    private static byte[][] INDEX_FIELDS;
    private int index = 0;

    public ArrayWriteState(WriteState parent, int mark) {
        super(parent, mark);
    }

    @Override
    public WriteState writeEndArray(BsonOutputStream out) throws IOException {
        return end(out);
    }

    @Override
    protected void writeField(byte type, BsonOutputStream out) throws IOException {
        byte[] encodedField = getField(index++);
        out.writeField(encodedField, type);
    }

    private byte[] getField(int index) {
        byte[][] indexFields = getIndexFields();
        if (index < indexFields.length) {
            return indexFields[index];
        }
        else {
            return encodeField(index);
        }
    }

    private byte[] encodeField(int index) {
        return Integer.toString(index).getBytes(BsonConstants.UTF8_CHARSET);
    }

    private byte[][] getIndexFields() {
        if (INDEX_FIELDS == null) {
            INDEX_FIELDS = new byte[256][];
            for (int i = 0; i < INDEX_FIELDS.length; i++) {
                INDEX_FIELDS[i] = encodeField(i);
            }
        }
        return INDEX_FIELDS;
    }
}
