package com.github.light.bson.write;

import java.io.IOException;

/**
 * Created by rob on 12-10-14.
 */
class StartBsonWriteState extends BsonWriteState {

    protected StartBsonWriteState(BsonBuffer buffer) {
        super(buffer);
    }

    @Override
    public BsonWriteState startObject() throws IOException {
        return new ObjectBsonWriteState(this);
    }

    @Override
    public BsonWriteState endObject() throws IOException {
        return null;
    }
}
