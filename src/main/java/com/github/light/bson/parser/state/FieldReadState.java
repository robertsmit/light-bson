package com.github.light.bson.parser.state;

import com.fasterxml.jackson.core.JsonToken;
import com.github.light.bson.parser.BsonToken;
import com.github.light.bson.util.BsonConstants;
import com.github.light.bson.util.BsonInputStream;
import com.github.light.bson.parser.state.value.LongValueReadState;
import com.github.light.bson.parser.state.value.StringValueReadState;
import com.github.light.bson.parser.state.value.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rob on 17-12-14.
 */
public class FieldReadState extends InnerReadState {
    private static Map<Byte, ValueReadState.Factory> factoryMap;
    private final byte type;
    private final String field;

    public FieldReadState(ReadState parent, byte type, String field) {
        super(parent);
        this.type = type;
        this.field = field;
    }

    @Override
    public BsonToken getCurrentToken() {
        return BsonToken.FIELD_NAME;
    }

    @Override
    public String getText() {
        return field;
    }

    @Override
    public String getCurrentName() {
        return getText();
    }

    @Override
    public ReadState next(BsonInputStream in) throws IOException {
        ReadState newParent = new EndFieldReadState(field, parent);
        return nextValue(in, type, newParent);
    }

    public static ReadState nextValue(BsonInputStream in, byte type, ReadState parent) throws IOException {
        return getValueReadStateFactoryMap().get(type).create(parent, in);
    }

    private static Map<Byte, ValueReadState.Factory> getValueReadStateFactoryMap() {
        if (factoryMap == null) {
            List<ValueReadState.Factory> factories = new ArrayList<ValueReadState.Factory>();
            factories.add(StringValueReadState.FACTORY);
            factories.add(LongValueReadState.FACTORY);
            factories.add(IntegerValueReadState.FACTORY);
            factories.add(DoubleValueReadState.FACTORY);
            factories.add(BooleanValueReadState.FACTORY);
            factories.add(DateValueReadState.FACTORY);
            factories.add(NullValueReadState.FACTORY);
            factories.add(DocumentReadState.OBJECT_FACTORY);
            factories.add(DocumentReadState.ARRAY_FACTORY);

            factoryMap = new HashMap<Byte, ValueReadState.Factory>(factories.size());
            for (ValueReadState.Factory factory : factories) {
                factoryMap.put(factory.getBsonType(), factory);
            }
        }
        return factoryMap;
    }
}
