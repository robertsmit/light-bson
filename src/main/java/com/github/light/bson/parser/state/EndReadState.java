package com.github.light.bson.parser.state;

import com.github.light.bson.util.BsonInputStream;

import java.io.IOException;

/**
 * Created by rob on 17-12-14.
 */
public class EndReadState extends AbstractReadState {
    public final static EndReadState INSTANCE = new EndReadState();

    private EndReadState() {
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public ReadState toValueContext(BsonInputStream in) {
        return this;
    }

    @Override
    public ReadState next(BsonInputStream in) throws IOException {
        return this;
    }
}
