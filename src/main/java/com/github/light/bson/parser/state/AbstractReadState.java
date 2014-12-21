package com.github.light.bson.parser.state;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonToken;
import com.github.light.bson.util.BsonInputStream;

import java.io.IOException;

/**
 * Created by rob on 17-12-14.
 */
public abstract class AbstractReadState implements ReadState {
    @Override
    public double getDoubleValue() {
        throw new IllegalStateException();
    }

    @Override
    public int getIntValue() throws JsonParseException {
        throw new IllegalStateException();
    }

    @Override
    public long getLongValue() {
        throw new IllegalStateException();
    }

    @Override
    public String getValueAsString(String def) {
        return def;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public ReadState skipChildren(BsonInputStream in) throws IOException {
        return this;
    }

    @Override
    public ReadState toValueContext(BsonInputStream in) throws IOException {
        return next(in).toValueContext(in);
    }

    public ReadState getParent() {
        return null;
    }

    @Override
    public JsonToken getCurrentToken() {
        return null;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public String getCurrentName() {
        return null;
    }
}
