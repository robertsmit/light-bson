package com.github.light.bson.parser.state.value;

import com.fasterxml.jackson.core.JsonToken;
import com.github.light.bson.util.BsonInputStream;
import com.github.light.bson.parser.state.ReadState;
import com.github.light.bson.util.BsonConstants;

import java.io.IOException;

/**
 * Created by rob on 19-12-14.
 */
public class BooleanValueReadState extends ValueReadState {
    public static final ValueReadState.Factory FACTORY = new Factory();

    private boolean value;

    public BooleanValueReadState(ReadState parent, Boolean value) {
        super(parent, value ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE);
        this.value = value;
    }

    @Override
    protected String valueString() {
        return Boolean.toString(value);
    }

    private static class Factory implements ValueReadState.Factory {
        @Override
        public ValueReadState create(ReadState parent, BsonInputStream in) throws IOException {
            Boolean value = in.readBoolean();
            return new BooleanValueReadState(parent, value);
        }

        @Override
        public byte getBsonType() {
            return BsonConstants.BOOLEAN;
        }
    }
}
