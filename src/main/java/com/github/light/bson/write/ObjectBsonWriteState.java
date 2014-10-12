package com.github.light.bson.write;

import java.io.IOException;

/**
 * Created by rob on 12-10-14.
 */
class ObjectBsonWriteState extends AbstractObjectBsonWriter {
    private String field;

    protected ObjectBsonWriteState(BsonWriteState previousState) {
        super(previousState);
    }

    @Override
    protected String popField() {
        String tempField = field;
        field = null;
        return tempField;
    }

    @Override
    public BsonWriteState endObject() throws IOException {
        return end();
    }

    @Override
    public BsonWriteState field(String name) {
        if (field != null) {
            throw new IllegalStateException();
        }
        field = name;
        return this;
    }
}
