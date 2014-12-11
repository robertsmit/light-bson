package com.github.light.bson.write;

import java.io.IOException;

/**
 * Created by rob on 20-10-14.
 */
public abstract class InnerWriteContext extends WriteContext {
    private final WriteContext parent;
    private final int mark;
    private final BsonStream out;

    public InnerWriteContext(WriteContext parent, int mark) {
        this.parent = parent;
        this.mark = mark;
        this.out = parent.getOut();
    }

    protected WriteContext end() throws IOException {
        out.endDocument(mark);
        return parent;
    }

    @Override
    public WriteContext writeString(String value) throws IOException {
        writeField(BsonConstants.STRING);
        out.writeString(value);
        return this;
    }

    @Override
    public WriteContext writeStartArray() throws IOException {
        writeField(BsonConstants.ARRAY);
        int mark = out.startDocument();
        return new ArrayWriteContext(this, mark);
    }

    @Override
    public WriteContext writeStartObject() throws IOException {
        writeField(BsonConstants.EMBEDDED);
        return super.writeStartObject();
    }

    @Override
    public WriteContext writeEndObject() throws IOException {
        out.endDocument(mark);
        return getParent();
    }

    @Override
    public WriteContext writeBoolean(boolean value) throws IOException {
        writeField(BsonConstants.BOOLEAN);
        out.writeBoolean(value);
        return this;
    }

    @Override
    public WriteContext writeNull() throws IOException {
        writeField(BsonConstants.NULL);
        return this;
    }

    @Override
    public WriteContext writeInt(int value) throws IOException {
        writeField(BsonConstants.INT32);
        out.writeInt(value);
        return this;
    }

    @Override
    public WriteContext writeLong(long value) throws IOException {
       writeField(BsonConstants.INT64);
        out.writeLong(value);
        return this;
    }

    @Override
    public WriteContext writeDatetime(long value) throws IOException {
        writeField(BsonConstants.UTC_DATE_TIME);
        out.writeLong(value);
        return this;
    }

    @Override
    public WriteContext writeDouble(double value) throws IOException {
        writeField(BsonConstants.DOUBLE);
        out.writeDouble(value);
        return this;
    }

    @Override
    public BsonStream getOut() {
        return out;
    }

    protected abstract byte[] popField();

    protected WriteContext getParent() {
        return parent;
    }

    private void writeField(byte type) throws IOException {
        out.writeField(popField(), type);
    }
}
