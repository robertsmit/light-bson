package com.github.light.bson.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by rob on 21-12-14.
 */
public class JsonReplayer {
    private JsonFactory factory;

    public JsonReplayer(JsonFactory factory) {
        this.factory = factory;
    }

    public byte[] replay(byte[] in) throws IOException {
        return replay(factory.createParser(in));
    }

    public byte[] replay(JsonParser parser) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        JsonGenerator generator = factory.createGenerator(out);
        replayOn(parser, generator);
        generator.close();
        return out.toByteArray();
    }

    private void replayOn(JsonParser parser, JsonGenerator generator) throws IOException {
        while (parser.nextToken() != null) {
            switch (parser.getCurrentToken()) {
                case START_OBJECT:
                    generator.writeStartObject();
                    break;
                case END_OBJECT:
                    generator.writeEndObject();
                    break;
                case START_ARRAY:
                    generator.writeStartArray();
                    break;
                case END_ARRAY:
                    generator.writeEndArray();
                    break;
                case VALUE_NULL:
                    generator.writeNull();
                    break;
                case VALUE_NUMBER_FLOAT:
                    generator.writeNumber(parser.getDoubleValue());
                    break;
                case VALUE_NUMBER_INT:
                    try {
                        generator.writeNumber(parser.getIntValue());
                    } catch (JsonParseException ex) {
                        generator.writeNumber(parser.getLongValue());
                    }
                    break;
                case FIELD_NAME:
                    generator.writeFieldName(parser.getText());
                    break;
                case VALUE_STRING:
                    generator.writeString(parser.getText());
                    break;
                case VALUE_FALSE:
                    generator.writeBoolean(false);
                    break;
                case VALUE_TRUE:
                    generator.writeBoolean(true);
                    break;
                default:
                    throw new IllegalArgumentException(parser.getCurrentToken().toString());
            }
        }
        parser.close();
    }
}
