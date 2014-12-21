package com.github.light.bson.parser.state.value;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonToken;
import com.github.light.bson.util.BsonInputStream;
import com.github.light.bson.parser.state.ReadState;
import com.github.light.bson.util.BsonConstants;
import com.google.common.primitives.Ints;

import java.io.IOException;

/**
 * Created by rob on 19-12-14.
 */
public class LongValueReadState extends ValueReadState {
    public static final ValueReadState.Factory FACTORY = new Factory();

    private final long value;

    public LongValueReadState(ReadState parent, long value) {
        super(parent, JsonToken.VALUE_NUMBER_INT);
        this.value = value;
    }

    @Override
    public int getIntValue() throws JsonParseException {
        int casted = (int) value;
        if (casted != value) {
            throw new JsonParseException("value cannot be cast to in", null, null);
        }
        return casted;
    }

    @Override
    public long getLongValue() {
        return value;
    }

    @Override
    public double getDoubleValue() {
        return value;
    }

    @Override
    protected String valueString() {
        return Long.toString(value);
    }

    private static class Factory implements ValueReadState.Factory {
        @Override
        public ValueReadState create(ReadState parent, BsonInputStream in) throws IOException {
            long value = in.readLong();
            return new LongValueReadState(parent, value);
        }

        @Override
        public byte getBsonType() {
            return BsonConstants.INT64;
        }
    }
}
