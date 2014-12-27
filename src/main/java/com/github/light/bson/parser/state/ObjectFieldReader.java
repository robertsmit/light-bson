package com.github.light.bson.parser.state;

import com.github.light.bson.util.BsonInputStream;

import java.io.IOException;

/**
 * Created by rob on 19-12-14.
 */
public class ObjectFieldReader implements DocumentFieldReader {
    @Override
    public ReadState next(BsonInputStream in, byte type, DocumentReadState parent) throws IOException {
        String field = in.readFieldString();
        return new FieldReadState(parent, type, field);
    }
}
