package com.github.light.bson.parser;

import com.fasterxml.jackson.core.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by rob on 17-12-14.
 */
public class JBsonParser extends JsonParser {
    private BsonParser parser;

    public JBsonParser(BsonParser parser) {
        this.parser = parser;
    }

    @Override
    public ObjectCodec getCodec() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCodec(ObjectCodec c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Version version() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() throws IOException {
        parser.close();
    }

    @Override
    public JsonToken nextToken() throws IOException, JsonParseException {
        BsonToken bsonToken = parser.nextToken();
        return bsonToken.jsonToken;
    }

    @Override
    public JsonToken nextValue() throws IOException, JsonParseException {
        return parser.nextValue().jsonToken;
    }

    @Override
    public JsonParser skipChildren() throws IOException, JsonParseException {
        parser.skipChildren();
        return this;
    }

    @Override
    public boolean isClosed() {
        return parser.isClosed();
    }

    @Override
    public JsonToken getCurrentToken() {
        return parser.getCurrentToken().jsonToken;
    }

    @Override
    public int getCurrentTokenId() {
        return getCurrentToken().id();
    }

    @Override
    public boolean hasCurrentToken() {
        return parser.getCurrentToken() != null;
    }

    @Override
    public String getCurrentName() throws IOException {
        return parser.getCurrentName();
    }

    @Override
    public JsonStreamContext getParsingContext() {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonLocation getTokenLocation() {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonLocation getCurrentLocation() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clearCurrentToken() {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonToken getLastClearedToken() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void overrideCurrentName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getText() throws IOException {
        return parser.getText();
    }

    @Override
    public char[] getTextCharacters() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getTextLength() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getTextOffset() throws IOException {
        return 0;
    }

    @Override
    public boolean hasTextCharacters() {
        return false;
    }

    @Override
    public Number getNumberValue() throws IOException {
        return null;
    }

    @Override
    public NumberType getNumberType() throws IOException {
        return null;
    }

    @Override
    public int getIntValue() throws IOException {
        return parser.getIntValue();
    }

    @Override
    public long getLongValue() throws IOException {
        return parser.getLongValue();
    }

    @Override
    public BigInteger getBigIntegerValue() throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public float getFloatValue() throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public double getDoubleValue() throws IOException {
        return parser.getDoubleValue();
    }

    @Override
    public BigDecimal getDecimalValue() throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public Object getEmbeddedObject() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] getBinaryValue(Base64Variant bv) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public String getValueAsString(String def) throws IOException {
        return parser.getValueAsString(def);
    }
}
