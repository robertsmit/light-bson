package com.github.light.bson.parser.state.value;

import com.fasterxml.jackson.core.JsonToken;
import com.github.light.bson.util.BsonInputStream;
import com.github.light.bson.parser.state.ReadState;
import com.github.light.bson.util.BsonConstants;

import java.io.IOException;

/**
 * Created by rob on 20-12-14.
 */
public class StringValueReadState extends ValueReadState {
    public static final ValueReadState.Factory FACTORY = new Factory();

    private String value;

    public StringValueReadState(ReadState parent, String value) {
        super(parent, JsonToken.VALUE_STRING);
        this.value = value;
    }

    @Override
    protected String valueString() {
        return value;
    }

    private static class Factory implements ValueReadState.Factory {

        @Override
        public ValueReadState create(ReadState parent, BsonInputStream in) throws IOException {
            String value = in.readString();
            return new StringValueReadState(parent, value);
        }

        @Override
        public byte getBsonType() {
            return BsonConstants.STRING;
        }
    }
}
