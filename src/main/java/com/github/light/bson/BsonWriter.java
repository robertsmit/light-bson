package com.github.light.bson;

import com.github.light.Writer;

import java.io.IOException;

/**
 * Created by rob on 12-10-14.
 */
public interface BsonWriter extends Writer<BsonWriter> {
    BsonWriter writeDatetime(long millis) throws IOException;
}
