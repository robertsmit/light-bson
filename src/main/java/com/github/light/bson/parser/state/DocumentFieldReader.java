package com.github.light.bson.parser.state;

import com.github.light.bson.util.BsonInputStream;

import java.io.IOException;

/**
 * Created by rob on 21-12-14.
 */
public interface DocumentFieldReader {
    ReadState next(BsonInputStream in, byte type, DocumentReadState parent) throws IOException;
}
