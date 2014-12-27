package com.github.light.bson.parser.state;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonToken;
import com.github.light.bson.parser.BsonToken;
import com.github.light.bson.util.BsonInputStream;

import java.io.IOException;
import java.util.Date;

/**
 * Created by rob on 17-12-14.
 */
public abstract class AbstractReadState implements ReadState {
    @Override
    public double getDoubleValue() {
        throw new IllegalStateException();
    }

    @Override
    public Date getDateValue() {
        throw new IllegalStateException();
    }

    @Override
    public int getIntValue() throws JsonParseException {
        throw new IllegalStateException();
    }

    @Override
    public long getLongValue() throws JsonParseException {
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
    public BsonToken getCurrentToken() {
        return BsonToken.NONE;
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
