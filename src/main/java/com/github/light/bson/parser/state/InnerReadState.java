package com.github.light.bson.parser.state;

import com.github.light.bson.util.BsonInputStream;

import java.io.IOException;

/**
 * Created by rob on 18-12-14.
 */
public abstract class InnerReadState extends AbstractReadState {
    protected final ReadState parent;

    public InnerReadState(ReadState parent) {
        this.parent = parent;
    }

    @Override
    public String getCurrentName() {
        return parent.getCurrentName();
    }

    public ReadState getParent() {
        return parent;
    }

    @Override
    public ReadState next(BsonInputStream in) throws IOException {
        return parent.next(in);
    }
}
