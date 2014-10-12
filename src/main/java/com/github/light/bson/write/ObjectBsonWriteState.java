package com.github.light.bson.write;

import java.io.IOException;

/**
 * Created by rob on 12-10-14.
 */
class ObjectBsonWriteState extends AbstractObjectBsonWriter {

    protected ObjectBsonWriteState(BsonWriteState previousState) {
        super(previousState);
    }

    @Override
    public BsonWriteState endObject() throws IOException {
        return end();
    }
}
