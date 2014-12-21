package com.github.light.bson.parser.state;

import com.github.light.bson.util.BsonInputStream;

import java.io.IOException;

/**
 * Created by rob on 17-12-14.
 */
public class StartReadState extends AbstractReadState {
    public final static StartReadState INSTANCE = new StartReadState();

    private StartReadState() {
    }

    @Override
    public ReadState next(BsonInputStream in) throws IOException {
        return DocumentReadState.OBJECT_FACTORY.create(EndReadState.INSTANCE, in);
    }
}
