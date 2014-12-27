package com.github.light.bson;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.IOContext;
import com.github.light.bson.parser.BsonParser;
import com.github.light.bson.parser.DefaultBsonParser;
import com.github.light.bson.parser.JBsonParser;
import com.github.light.bson.util.BsonOutputStream;
import com.github.light.bson.generator.DefaultBsonGenerator;
import com.github.light.bson.generator.JBsonGenerator;
import com.github.light.bson.util.BsonInputStream;
import com.github.light.bson.util.FieldCache;

import java.io.*;

/**
 * Created by rob on 11-12-14.
 */
public class JBsonFactory extends JsonFactory {
    private FieldCache fieldCache;

    public JBsonFactory() {
        fieldCache = new FieldCache();
    }

    public JBsonFactory(ObjectCodec oc) {
        super(oc);
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
        BsonOutputStream bsonOut = new BsonOutputStream(out, fieldCache);
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
