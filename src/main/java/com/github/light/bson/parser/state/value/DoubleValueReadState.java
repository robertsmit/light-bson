package com.github.light.bson.parser.state.value;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonToken;
import com.github.light.bson.parser.BsonToken;
import com.github.light.bson.util.BsonInputStream;
import com.github.light.bson.parser.state.ReadState;
import com.github.light.bson.util.BsonConstants;

import java.io.IOException;

/**
 * Created by rob on 19-12-14.
 */
public class DoubleValueReadState extends ValueReadState {
    public static final ValueReadState.Factory FACTORY = new Factory();

    private double value;

    public DoubleValueReadState(ReadState parent, Double value) {
        super(parent, BsonToken.VALUE_NUMBER_FLOAT);
        this.value = value;
    }

    @Override
    public double getDoubleValue() {
        return value;
    }

    @Override
    public long getLongValue() throws JsonParseException {
        long casted = (long) value;
        if (casted != value) {
            throw new JsonParseException("value cannot be cast to long", null, null);
        }
        return casted;
    }

    @Override
    protected String valueString() {
        return Double.toString(value);
    }

    private static class Factory implements ValueReadState.Factory {
        @Override
        public ValueReadState create(ReadState parent, BsonInputStream in) throws IOException {
            double value = in.readDouble();
            return new DoubleValueReadState(parent, value);
        }

        @Override
        public byte getBsonType() {
            return BsonConstants.DOUBLE;
        }
    }
}
