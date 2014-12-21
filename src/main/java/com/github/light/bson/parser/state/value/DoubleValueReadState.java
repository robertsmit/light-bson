package com.github.light.bson.parser.state.value;

import com.fasterxml.jackson.core.JsonToken;
import com.github.light.bson.util.BsonInputStream;
import com.github.light.bson.parser.state.ReadState;
import com.github.light.bson.util.BsonConstants;

import java.io.IOException;

/**
 * Created by rob on 19-12-14.
 */
public class DoubleValueReadState extends ValueReadState {
    public static final ValueReadState.Factory FACTORY = new Factory();

    private Double value;

    public DoubleValueReadState(ReadState parent, Double value) {
        super(parent, JsonToken.VALUE_NUMBER_FLOAT);
        this.value = value;
    }

    @Override
    public double getDoubleValue() {
        return value;
    }

    @Override
    protected String valueString() {
        return Double.toString(value);
    }

    private static class Factory implements ValueReadState.Factory {
        @Override
        public ValueReadState create(ReadState parent, BsonInputStream in) throws IOException {
            Double value = in.readDouble();
            return new DoubleValueReadState(parent, value);
        }

        @Override
        public byte getBsonType() {
            return BsonConstants.DOUBLE;
        }
    }
}
