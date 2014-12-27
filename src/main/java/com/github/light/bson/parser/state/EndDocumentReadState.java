package com.github.light.bson.parser.state;

import com.fasterxml.jackson.core.JsonToken;
import com.github.light.bson.parser.BsonToken;

/**
 * Created by rob on 19-12-14.
 */
public class EndDocumentReadState extends InnerReadState {
    private BsonToken token;

    public EndDocumentReadState(BsonToken token, ReadState parent) {
        super(parent);
        this.token = token;
    }

    @Override
    public BsonToken getCurrentToken() {
        return token;
    }

    @Override
    public String getText() {
        return token.toString();
    }
}
