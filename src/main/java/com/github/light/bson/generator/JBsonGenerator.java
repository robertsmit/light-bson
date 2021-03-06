package com.github.light.bson.generator;

import com.fasterxml.jackson.core.*;
import com.github.light.bson.util.BsonConstants;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by rob on 19-10-14.
 */
public class JBsonGenerator extends JsonGenerator {
    private BsonGenerator generator;
    private boolean isClosed;

    public JBsonGenerator(BsonGenerator generator) {
        this.generator = generator;
    }

    @Override
    public JsonGenerator setCodec(ObjectCodec oc) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ObjectCodec getCodec() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Version version() {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonGenerator enable(Feature f) {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonGenerator disable(Feature f) {
        System.out.println("disable: " + f);
        return this;
    }

    @Override
    public boolean isEnabled(Feature f) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getFeatureMask() {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonGenerator setFeatureMask(int mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonGenerator useDefaultPrettyPrinter() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeStartArray() throws IOException {
        generator.writeStartArray();
    }

    @Override
    public void writeEndArray() throws IOException {
        generator.writeEndArray();
    }

    @Override
    public void writeStartObject() throws IOException {
        generator.writeStartObject();
    }

    @Override
    public void writeEndObject() throws IOException {
        generator.writeEndObject();
    }

    @Override
    public void writeFieldName(String name) throws IOException {
        generator.writeField(name);
    }

    @Override
    public void writeFieldName(SerializableString name) throws IOException {
        writeFieldName(name.getValue());
    }

    @Override
    public void writeString(String text) throws IOException {
        generator.writeString(text);
    }

    @Override
    public void writeString(char[] text, int offset, int len) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeString(SerializableString text) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeRawUTF8String(byte[] text, int offset, int length) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeUTF8String(byte[] text, int offset, int length) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeRaw(String text) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeRaw(String text, int offset, int len) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeRaw(char[] text, int offset, int len) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeRaw(char c) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeRawValue(String text) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeRawValue(String text, int offset, int len) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeRawValue(char[] text, int offset, int len) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeBinary(Base64Variant bv, byte[] data, int offset, int len) throws IOException {
        generator.writeBinary(BsonConstants.BINARY_SUBTYPE_GENERIC, data, offset, len);
    }

    @Override
    public int writeBinary(Base64Variant bv, InputStream data, int dataLength) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeNumber(int v) throws IOException {
        generator.writeNumber(v);
    }

    @Override
    public void writeNumber(long v) throws IOException {
        generator.writeNumber(v);
    }

    @Override
    public void writeNumber(BigInteger v) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeNumber(double d) throws IOException {
        generator.writeNumber(d);
    }

    @Override
    public void writeNumber(float f) throws IOException {
        generator.writeNumber(f);
    }

    @Override
    public void writeNumber(BigDecimal dec) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeNumber(String encodedValue) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeBoolean(boolean state) throws IOException {
        generator.writeBoolean(state);
    }

    @Override
    public void writeNull() throws IOException {
        generator.writeNull();
    }

    public void writeDateTime(long millis) throws IOException {
        generator.writeDatetime(millis);
    }

    public void writeDateTime(Date date) throws IOException {
        generator.writeDatetime(date);
    }

    @Override
    public void writeObject(Object pojo) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeTree(TreeNode rootNode) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonStreamContext getOutputContext() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public boolean isClosed() {
        return isClosed;
    }

    @Override
    public void close() throws IOException {
        isClosed = true;
        generator.close();
    }


}
