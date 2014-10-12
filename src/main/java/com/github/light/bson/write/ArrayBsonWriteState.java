package com.github.light.bson.write;

import java.io.IOException;

/**
 * Created by rob on 12-10-14.
 */
class ArrayBsonWriteState extends AbstractObjectBsonWriter {
    private static String[] indexNames;

    private int index = 0;

    protected ArrayBsonWriteState(BsonWriteState previousState) {
        super(previousState);
    }

    @Override
    public BsonWriteState writeNumber(double value) throws IOException {
        return nextField().writeNumber(value);
    }

    @Override
    public BsonWriteState writeString(String value) throws IOException {
        return nextField().writeString(value);
    }

    @Override
    public BsonWriteState startArray() throws IOException {
        return nextField().startArray();
    }

    @Override
    public BsonWriteState endArray() throws IOException {
        return end();
    }

    @Override
    public BsonWriteState startObject() throws IOException {
        return nextField().startObject();
    }

    @Override
    public BsonWriteState writeBoolean(boolean value) throws IOException {
        return nextField().writeBoolean(value);
    }

    @Override
    public BsonWriteState writeNull() throws IOException {
        return nextField().writeNull();
    }

    @Override
    public BsonWriteState writeNumber(short value) throws IOException {
        return nextField().writeNumber(value);
    }

    @Override
    public BsonWriteState writeNumber(int value) throws IOException {
        return nextField().writeNumber(value);
    }

    @Override
    public BsonWriteState writeNumber(long value) throws IOException {
        return nextField().writeNumber(value);
    }

    @Override
    public BsonWriteState writeDatetime(long millis) throws IOException {
        return nextField().writeDatetime(millis);
    }

    private FieldBsonWriteState nextField() {
        String fieldName = getFieldName();
        index++;
        return new FieldBsonWriteState(fieldName, this);
    }

    private String getFieldName() {
        if (indexNames == null) {
            initializeIndexNames();
        }
        if (indexNames.length > index) {
            return indexNames[index];
        }
        else {
            return Integer.toString(index);
        }
    }

    private void initializeIndexNames() {
        indexNames = new String[100];
        for (int i = 0; i < indexNames.length; i++) {
            indexNames[i] = Integer.toString(i);
        }
    }
}
