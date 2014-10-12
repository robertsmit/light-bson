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
    public BsonWriteState endArray() throws IOException {
        return end();
    }


    protected String popField() {
        if (indexNames == null) {
            initializeIndexNames();
        }
        if (index < indexNames.length) {
            return indexNames[index++];
        }
        else {
            return Integer.toString(index++);
        }
    }

    private void initializeIndexNames() {
        indexNames = new String[100];
        for (int i = 0; i < indexNames.length; i++) {
            indexNames[i] = Integer.toString(i);
        }
    }
}
