package com.github.light.bson.parser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonToken;
import com.github.light.bson.parser.state.ReadState;
import com.github.light.bson.parser.state.StartReadState;
import com.github.light.bson.util.BsonInputStream;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rob on 18-12-14.
 */
public class DefaultBsonParser implements BsonParser {
    private ReadState state;
    private boolean isClosed;
    private BsonInputStream in;

    public DefaultBsonParser(BsonInputStream in) {
        this.in = in;
        state = StartReadState.INSTANCE;
    }

    @Override
    public void close() throws IOException {
        try {
            in.close();
        } finally {
            isClosed = true;
        }
    }

    @Override
    public String getCurrentName() {
        return state.getCurrentName();
    }

    @Override
    public JsonToken getCurrentToken() {
        return state.getCurrentToken();
    }

    @Override
    public double getDoubleValue() {
        return state.getDoubleValue();
    }

    @Override
    public int getIntValue() throws JsonParseException {
        return state.getIntValue();
    }

    @Override
    public long getLongValue() {
        return state.getLongValue();
    }

    @Override
    public String getText() {
        return state.getText();
    }

    @Override
    public String getvalueAsString(String def) {
        return state.getValueAsString(def);
    }

    @Override
    public boolean isClosed() {
        return isClosed || !state.hasNext();
    }

    @Override
    public JsonToken nextToken() throws IOException {
        state = state.next(in);
        return state.getCurrentToken();
    }

    @Override
    public JsonToken nextValue() throws IOException {
        state = state.next(in).toValueContext(in);
        return state.getCurrentToken();
    }

    @Override
    public BsonParser skipChildren() throws IOException {
        state = state.skipChildren(in);
        return this;
    }
}
