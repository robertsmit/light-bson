package com.github.light.bson.util;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;

/**
 * Created by rob on 21-12-14.
 */
public interface JsonWriter {
    void write(JsonGenerator generator) throws IOException;
}