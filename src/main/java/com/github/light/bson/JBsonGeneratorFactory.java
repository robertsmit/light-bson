package com.github.light.bson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
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
public class JBsonGeneratorFactory extends JsonFactory {
    private FieldCache fieldCache;

    public JBsonGeneratorFactory() {
        fieldCache = new FieldCache();
    }

    @Override
    public JsonGenerator createGenerator(OutputStream out) {
        BsonOutputStream bsonOut = new BsonOutputStream(out, fieldCache);
        return new JBsonGenerator(new DefaultBsonGenerator(bsonOut));
    }

    @Override
    public JsonParser createParser(InputStream in) throws IOException {
        BsonInputStream bsonIn = new BsonInputStream(in, fieldCache);
        BsonParser parser = new DefaultBsonParser(bsonIn);
        return new JBsonParser(parser);
    }

    @Override
    public JsonParser createParser(byte[] bytesIn) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytesIn);
        return createParser(in);
    }
}
