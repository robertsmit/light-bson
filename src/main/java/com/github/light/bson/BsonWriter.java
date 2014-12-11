package com.github.light.bson;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by rob on 12-10-14.
 */
public interface BsonWriter extends Closeable {
    BsonWriter writeField(String name) throws IOException;
    BsonWriter writeString(String value) throws IOException;
    BsonWriter writeStartArray() throws IOException;
    BsonWriter writeEndArray() throws IOException;
    BsonWriter writeStartObject() throws IOException;
    BsonWriter writeEndObject() throws IOException;
    BsonWriter writeBoolean(boolean value) throws IOException;
    BsonWriter writeNull() throws IOException;
    BsonWriter writeNumber(short value) throws IOException;
    BsonWriter writeNumber(double value) throws IOException;
    BsonWriter writeNumber(int value) throws IOException;
    BsonWriter writeNumber(long value) throws IOException;
    BsonWriter writeDatetime(long millis) throws IOException;
}
