package com.github.light.bson.write;

import java.io.IOException;

/**
 * Created by rob on 12-10-14.
 */
class FieldBsonWriteState extends BsonWriteState {
    private String name;

    protected FieldBsonWriteState(String name, BsonWriteState previousState) {
        super(previousState);
        this.name = name;
    }

    @Override
    public BsonWriteState writeString(String value) throws IOException {
        writeField(BsonConstants.STRING).putString(value);
        return previousState;
    }

    @Override
    public BsonWriteState writeNumber(double value) throws IOException {
        writeField(BsonConstants.DOUBLE).putDouble(value);
        return previousState;
    }

    @Override
    public BsonWriteState startArray() throws IOException {
        writeField(BsonConstants.ARRAY);
        return new ArrayBsonWriteState(previousState);
    }

    @Override
    public BsonWriteState startObject() throws IOException {
        writeField(BsonConstants.EMBEDDED);
        return new ObjectBsonWriteState(previousState);
    }

    @Override
    public BsonWriteState writeBoolean(boolean value) throws IOException {
        byte b = value ? BsonConstants.TRUE : BsonConstants.FALSE;
        writeField(BsonConstants.BOOLEAN).putByte(b);
        return previousState;
    }

    @Override
    public BsonWriteState writeNull() throws IOException {
        writeField(BsonConstants.NULL);
        return previousState;
    }

    @Override
    public BsonWriteState writeNumber(int value) throws IOException {
        writeField(BsonConstants.INT32).putInt(value);
        return previousState;
    }

    @Override
    public BsonWriteState writeNumber(long value) throws IOException {
        writeField(BsonConstants.INT64).putLong(value);
        return previousState;
    }

    @Override
    public BsonWriteState writeNumber(short value) throws IOException {
        return writeNumber((int) value);
    }

    @Override
    public BsonWriteState writeDatetime(long millis) throws IOException {
        writeField(BsonConstants.UTC_DATE_TIME).putLong(millis);
        return previousState;
    }

    private BsonBuffer writeField(byte type) throws IOException {
        return buffer.putField(name, type);
    }
}
