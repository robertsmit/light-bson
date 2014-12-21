package com.github.light.bson.generator;

import com.fasterxml.jackson.core.*;
import com.github.light.bson.generator.BsonGenerator;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by rob on 19-10-14.
 */
public class JBsonGenerator extends JsonGenerator {
    private BsonGenerator writer;
    private boolean isClosed;

    public JBsonGenerator(BsonGenerator writer) {
        this.writer = writer;
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
        throw new UnsupportedOperationException();
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
        writer.writeStartArray();
    }

    @Override
    public void writeEndArray() throws IOException {
        writer.writeEndArray();
    }

    @Override
    public void writeStartObject() throws IOException {
        writer.writeStartObject();
    }

    @Override
    public void writeEndObject() throws IOException {
        writer.writeEndObject();
    }

    @Override
    public void writeFieldName(String name) throws IOException {
        writer.writeField(name);
    }

    @Override
    public void writeFieldName(SerializableString name) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeString(String text) throws IOException {
        writer.writeString(text);
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
        throw new UnsupportedOperationException();
    }

    @Override
    public int writeBinary(Base64Variant bv, InputStream data, int dataLength) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeNumber(int v) throws IOException {
        writer.writeNumber(v);
    }

    @Override
    public void writeNumber(long v) throws IOException {
        writer.writeNumber(v);
    }

    @Override
    public void writeNumber(BigInteger v) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeNumber(double d) throws IOException {
        writer.writeNumber(d);
    }

    @Override
    public void writeNumber(float f) throws IOException {
        writer.writeNumber(f);
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
        writer.writeBoolean(state);
    }

    @Override
    public void writeNull() throws IOException {
        writer.writeNull();
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
        writer.close();
    }
}
