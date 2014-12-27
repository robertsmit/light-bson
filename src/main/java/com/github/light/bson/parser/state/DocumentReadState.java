package com.github.light.bson.parser.state;

import com.github.light.bson.parser.BsonToken;
import com.github.light.bson.util.BsonInputStream;
import com.github.light.bson.parser.state.value.ValueReadState;
import com.github.light.bson.util.BsonConstants;

import java.io.IOException;

/**
 * Created by rob on 19-12-14.
 */
public class DocumentReadState extends InnerReadState {
    public static final ValueReadState.Factory ARRAY_FACTORY = new ArrayFactory();
    public static final ValueReadState.Factory OBJECT_FACTORY = new ObjectFactory();

    private BsonToken startToken;
    private BsonToken endToken;
    private int size;
    private DocumentFieldReader fieldReader;

    private DocumentReadState(BsonToken startToken, BsonToken endToken, DocumentFieldReader fieldReader, int size, ReadState parent) throws IOException {
        super(parent);
        this.startToken = startToken;
        this.endToken = endToken;
        this.fieldReader = fieldReader;
        this.size = size;
    }

    @Override
    public BsonToken getCurrentToken() {
        return startToken;
    }

    @Override
    public String getText() {
        return startToken.toString();
    }

    @Override
    public ReadState toValueContext(BsonInputStream in) throws IOException {
        return this;
    }

    @Override
    public ReadState skipChildren(BsonInputStream in) throws IOException {
        in.skip(size);
        return parent.next(in);
    }

    @Override
    public ReadState next(BsonInputStream in) throws IOException {
        byte type = in.readByte();
        if (type == 0) {
            return new EndDocumentReadState(endToken, parent);
        }
        return fieldReader.next(in, type, this);
    }

    private static class ArrayFactory implements ValueReadState.Factory {
        @Override
        public ReadState create(ReadState parent, BsonInputStream in) throws IOException {
            int size = in.readInt();
            return new DocumentReadState(BsonToken.START_ARRAY,
                    BsonToken.END_ARRAY, new ArrayFieldReader(), size, parent);
        }

        @Override
        public byte getBsonType() {
            return BsonConstants.ARRAY;
        }
    }

    private static class ObjectFactory implements ValueReadState.Factory {
        @Override
        public ReadState create(ReadState parent, BsonInputStream in) throws IOException {
            int size = in.readInt();
            return new DocumentReadState(BsonToken.START_OBJECT,
                    BsonToken.END_OBJECT, new ObjectFieldReader(), size, parent);
        }

        @Override
        public byte getBsonType() {
            return BsonConstants.OBJECT;
        }
    }
}
