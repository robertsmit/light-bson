package com.github.light.bson;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.IOContext;
import com.github.light.bson.parser.BsonParser;
import com.github.light.bson.parser.DefaultBsonParser;
import com.github.light.bson.parser.JBsonParser;
import com.github.light.bson.util.*;
import com.github.light.bson.generator.DefaultBsonGenerator;
import com.github.light.bson.generator.JBsonGenerator;

import java.io.*;

/**
 * Created by rob on 11-12-14.
 */
public class JBsonFactory extends JsonFactory {
    private FieldCache fieldCache;
    private boolean streamingEnabled = false;

    public JBsonFactory() {
        fieldCache = new FieldCache();
    }

    public JBsonFactory(ObjectCodec oc) {
        super(oc);
    }

    public JBsonFactory enableStreaming(boolean streamingEnabled) {
        this.streamingEnabled = streamingEnabled;
        return this;
    }

    @Override
    public JBsonGenerator createGenerator(OutputStream out) throws IOException {
        return createGenerator(out, JsonEncoding.UTF8);
    }

    @Override
    public JBsonGenerator createGenerator(OutputStream out, JsonEncoding enc) throws IOException {
        if (enc != JsonEncoding.UTF8) {
            throw new IllegalStateException();
        }
        if (streamingEnabled) {
            return createStreamingGenerator(out);
        }
        else {
            return createBufferingGenerator(out);
        }
    }

    public JBsonGenerator createStreamingGenerator(OutputStream out) throws IOException {
        BsonOutputStream bsonOut = new StreamingBsonOutputStream(out, fieldCache);
        return createGenerator(bsonOut);
    }

    public JBsonGenerator createBufferingGenerator(OutputStream out) throws IOException {
        BsonOutputStream bsonOut = new BufferingBsonOutputStream(out, fieldCache);
        return createGenerator(bsonOut);
    }

    private JBsonGenerator createGenerator(BsonOutputStream bsonOut) {
        return new JBsonGenerator(new DefaultBsonGenerator(bsonOut));
    }

    @Override
    public JBsonParser createParser(InputStream in) throws IOException {
        BsonInputStream bsonIn = new BsonInputStream(in, fieldCache);
        BsonParser parser = new DefaultBsonParser(bsonIn);
        return new JBsonParser(parser);
    }

    @Override
    public JBsonParser createParser(byte[] bytesIn) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytesIn);
        return createParser(in);
    }

    @Override
    protected JsonParser _createParser(InputStream in, IOContext ctxt) throws IOException {
        return super._createParser(in, ctxt);
    }        
}
