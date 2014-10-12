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

    protected BsonWriteState end() throws IOException {
        buffer.putByte(BsonConstants.TERMINATOR);
        int size = buffer.position() - mark;
        buffer.putInt(mark, size);
        return previousState;
    }

    @Override
    public BsonWriteState writeString(String value) throws IOException {
        writeField(BsonConstants.STRING).putString(value);
        return this;
    }

    @Override
    public BsonWriteState writeNumber(double value) throws IOException {
        writeField(BsonConstants.DOUBLE).putDouble(value);
        return this;
    }

    @Override
    public BsonWriteState startArray() throws IOException {
        writeField(BsonConstants.ARRAY);
        return new ArrayBsonWriteState(this);
    }

    @Override
    public BsonWriteState startObject() throws IOException {
        writeField(BsonConstants.EMBEDDED);
        return new ObjectBsonWriteState(this);
    }

    @Override
    public BsonWriteState writeBoolean(boolean value) throws IOException {
        byte b = value ? BsonConstants.TRUE : BsonConstants.FALSE;
        writeField(BsonConstants.BOOLEAN).putByte(b);
        return this;
    }

    @Override
    public BsonWriteState writeNull() throws IOException {
        writeField(BsonConstants.NULL);
        return this;
    }

    @Override
    public BsonWriteState writeNumber(int value) throws IOException {
        writeField(BsonConstants.INT32).putInt(value);
        return this;
    }

    @Override
    public BsonWriteState writeNumber(long value) throws IOException {
        writeField(BsonConstants.INT64).putLong(value);
        return this;
    }

    @Override
    public BsonWriteState writeNumber(short value) throws IOException {
        return writeNumber((int) value);
    }

    @Override
    public BsonWriteState writeDatetime(long millis) throws IOException {
        writeField(BsonConstants.UTC_DATE_TIME).putLong(millis);
        return this;
    }

    protected abstract String popField();

    private BsonBuffer writeField(byte type) throws IOException {
        buffer.putField(popField(), type);
        return buffer;
    }

}
