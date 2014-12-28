package com.github.light.bson.generator.state;

import com.github.light.bson.util.BsonConstants;
import com.github.light.bson.util.BsonOutputStream;

import java.io.IOException;

/**
 * Created by rob on 20-10-14.
 */
public abstract class InnerWriteState extends WriteState {
    private final WriteState parent;
    private final int mark;

    public InnerWriteState(WriteState parent, int mark) {
        this.parent = parent;
        this.mark = mark;
    }

    protected WriteState end(BsonOutputStream out) throws IOException {
        out.endDocument(mark);
        return parent;
    }

    @Override
    public WriteState writeString(String value, BsonOutputStream out) throws IOException {
        writeField(BsonConstants.STRING, out);
        out.writeString(value);
        return this;
    }

    @Override
    public WriteState writeBinary(BsonOutputStream out, byte subtype, byte[] value, int offset, int length) throws IOException {
        writeField(BsonConstants.BINARY, out);
        out.writeBinary(subtype, value, offset, length);
        return this;
    }

    @Override
    public WriteState writeStartArray(BsonOutputStream out) throws IOException {
        writeField(BsonConstants.ARRAY, out);
        int mark = out.startDocument();
        return new ArrayWriteState(this, mark);
    }

    @Override
    public WriteState writeStartObject(BsonOutputStream out) throws IOException {
        writeField(BsonConstants.OBJECT, out);
        return super.writeStartObject(out);
    }

    @Override
    public WriteState writeBoolean(boolean value, BsonOutputStream out) throws IOException {
        writeField(BsonConstants.BOOLEAN, out);
        out.writeBoolean(value);
        return this;
    }

    @Override
    public WriteState writeNull(BsonOutputStream out) throws IOException {
        writeField(BsonConstants.NULL, out);
        return this;
    }

    @Override
    public WriteState writeInt(int value, BsonOutputStream out) throws IOException {
        writeField(BsonConstants.INT32, out);
        out.writeInt(value);
        return this;
    }

    @Override
    public WriteState writeLong(long value, BsonOutputStream out) throws IOException {
        writeField(BsonConstants.INT64, out);
        out.writeLong(value);
        return this;
    }

    @Override
    public WriteState writeDatetime(long value, BsonOutputStream out) throws IOException {
        writeField(BsonConstants.UTC_DATE_TIME, out);
        out.writeLong(value);
        return this;
    }

    @Override
    public WriteState writeDouble(double value, BsonOutputStream out) throws IOException {
        writeField(BsonConstants.DOUBLE, out);
        out.writeDouble(value);
        return this;
    }

    protected WriteState getParent() {
        return parent;
    }

    protected abstract void writeField(byte type, BsonOutputStream out) throws IOException;
}
