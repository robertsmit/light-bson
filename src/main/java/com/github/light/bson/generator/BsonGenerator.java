package com.github.light.bson.generator;

import java.io.Closeable;
import java.io.IOException;
import java.util.Date;

/**
 * Created by rob on 12-10-14.
 */
public interface BsonGenerator extends Closeable {
    BsonGenerator writeField(String name) throws IOException;
    BsonGenerator writeString(String value) throws IOException;
    BsonGenerator writeStartArray() throws IOException;
    BsonGenerator writeEndArray() throws IOException;
    BsonGenerator writeStartObject() throws IOException;
    BsonGenerator writeEndObject() throws IOException;
    BsonGenerator writeBoolean(boolean value) throws IOException;
    BsonGenerator writeNull() throws IOException;
    BsonGenerator writeNumber(short value) throws IOException;
    BsonGenerator writeNumber(double value) throws IOException;
    BsonGenerator writeNumber(int value) throws IOException;
    BsonGenerator writeNumber(long value) throws IOException;
    BsonGenerator writeDatetime(long millis) throws IOException;
    BsonGenerator writeDatetime(Date date) throws IOException;
}
