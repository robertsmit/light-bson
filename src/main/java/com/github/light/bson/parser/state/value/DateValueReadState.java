package com.github.light.bson.parser.state.value;

import com.github.light.bson.parser.BsonToken;
import com.github.light.bson.parser.state.ReadState;
import com.github.light.bson.util.BsonConstants;
import com.github.light.bson.util.BsonInputStream;

import java.io.IOException;
import java.util.Date;

/**
 * Created by rob on 27-12-14.
 */
public class DateValueReadState extends ValueReadState {
    public static final ValueReadState.Factory FACTORY = new Factory();
    private final long value;

    public DateValueReadState(ReadState parent, long value) {
        super(parent, BsonToken.VALUE_DATE_TIME);
        this.value = value;
    }

    @Override
    public long getLongValue() {
        return value;
    }

    @Override
    public Date getDateValue() {
        return new Date(value);
    }

    @Override
    protected String valueString() {
        return new Date(value).toString();
    }

    private static class Factory implements ValueReadState.Factory {
        @Override
        public ValueReadState create(ReadState parent, BsonInputStream in) throws IOException {
            long value = in.readLong();
            return new DateValueReadState(parent, value);
        }

        @Override
        public byte getBsonType() {
            return BsonConstants.UTC_DATE_TIME;
        }
    }
}
