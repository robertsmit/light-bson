package com.github.light.bson.parser.state;

import com.fasterxml.jackson.core.JsonToken;

/**
 * Created by rob on 19-12-14.
 */
public class EndDocumentReadState extends InnerReadState {
    private JsonToken token;

    public EndDocumentReadState(JsonToken token, ReadState parent) {
        super(parent);
        this.token = token;
    }

    @Override
    public JsonToken getCurrentToken() {
        return token;
    }

    @Override
    public String getText() {
        return token.toString();
    }
}
