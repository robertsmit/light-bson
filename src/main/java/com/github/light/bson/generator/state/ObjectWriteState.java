package com.github.light.bson.generator.state;

import com.github.light.bson.util.BsonOutputStream;

import java.io.IOException;

/**
 * Created by rob on 20-10-14.
 */
public class ObjectWriteState extends InnerWriteState {
    private String field;

    public ObjectWriteState(WriteState parent, int mark) {
        super(parent, mark);
    }

    @Override
    public WriteState writeEndObject(BsonOutputStream out) throws IOException {
        return end(out);
    }

    @Override
    public WriteState writeField(String name, BsonOutputStream out) throws IOException {
        if (field != null) {
            throw new IllegalStateException();
        }
        field = name;
        return this;
    }

    @Override
    protected void writeField(byte type, BsonOutputStream out) throws IOException {
        if (field == null) {
            throw new IllegalStateException();
        }
        try {
            out.writeField(field, type);
        }
        finally {
            field = null;
        }
    }
}
