package com.github.light.bson.write;

import java.io.IOException;

/**
 * Created by rob on 12-10-14.
 */
abstract class AbstractObjectBsonWriter extends BsonWriteState {
    private int mark;

    protected AbstractObjectBsonWriter(BsonWriteState previousState) {
        super(previousState);
        mark = buffer.position();
        buffer.position(mark + 4);
    }

    @Override
    public BsonWriteState field(String name) {
        return new FieldBsonWriteState(name, this);
    }

    protected BsonWriteState end() throws IOException {
        buffer.putByte(BsonConstants.TERMINATOR);
        int size = buffer.position() - mark;
        buffer.putInt(mark, size);
        return previousState;
    }

    @Override
    public BsonWriteState writeStringField(String field, String value) throws IOException {
        return field(field).writeString(value);
    }
}
