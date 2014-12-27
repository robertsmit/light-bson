package com.github.light.bson.parser.state.value;

import com.fasterxml.jackson.core.JsonToken;
import com.github.light.bson.parser.BsonToken;
import com.github.light.bson.util.BsonInputStream;
import com.github.light.bson.parser.state.ReadState;
import com.github.light.bson.util.BsonConstants;

import java.io.IOException;

/**
 * Created by rob on 20-12-14.
 */
public class NullValueReadState extends ValueReadState {
    public static final ValueReadState.Factory FACTORY = new Factory();

    public NullValueReadState(ReadState parent) {
        super(parent, BsonToken.VALUE_NULL);
    }

    @Override
    protected String valueString() {
        return "";
    }

    private static class Factory implements ValueReadState.Factory {

        @Override
        public ValueReadState create(ReadState parent, BsonInputStream in) throws IOException {
            return new NullValueReadState(parent);
        }

        @Override
        public byte getBsonType() {
            return BsonConstants.NULL;
        }
    }

}
