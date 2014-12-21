package com.github.light.bson.parser.state.value;

import com.fasterxml.jackson.core.JsonToken;
import com.github.light.bson.util.BsonInputStream;
import com.github.light.bson.parser.state.ReadState;
import com.github.light.bson.util.BsonConstants;

import java.io.IOException;

/**
 * Created by rob on 19-12-14.
 */
public class IntegerValueReadState extends ValueReadState {
    public static final Factory FACTORY = new Factory();

    private Integer value;

    public IntegerValueReadState(ReadState parent, Integer value) {
        super(parent, JsonToken.VALUE_NUMBER_INT);
        this.value = value;
    }

    @Override
    public int getIntValue() {
        return value;
    }

    @Override
    public double getDoubleValue() {
        return value;
    }

    @Override
    public long getLongValue() {
        return value;
    }

    @Override
    protected String valueString() {
        return Integer.toString(value);
    }

    private static class Factory implements ValueReadState.Factory {
        @Override
        public ValueReadState create(ReadState parent, BsonInputStream in) throws IOException {
            int value = in.readInt();
            return new IntegerValueReadState(parent, value);
        }

        @Override
        public byte getBsonType() {
            return BsonConstants.INT32;
        }
    }
}
