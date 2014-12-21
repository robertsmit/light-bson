package com.github.light.bson.parser.state.value;

import com.fasterxml.jackson.core.JsonToken;
import com.github.light.bson.util.BsonInputStream;
import com.github.light.bson.parser.state.InnerReadState;
import com.github.light.bson.parser.state.ReadState;

import java.io.IOException;

/**
 * Created by rob on 19-12-14.
 */
public abstract class ValueReadState extends InnerReadState {
    private JsonToken token;

    public ValueReadState(ReadState parent, JsonToken token) {
        super(parent);
        this.token = token;
    }

    @Override
    public JsonToken getCurrentToken() {
        return token;
    }

    @Override
    public String getText() {
        return valueString();
    }

    @Override
    public ReadState toValueContext(BsonInputStream in) throws IOException {
        return this;
    }

    protected abstract String valueString();

    public static interface Factory {
        ReadState create(ReadState parent, BsonInputStream in) throws IOException;

        byte getBsonType();
    }
}
