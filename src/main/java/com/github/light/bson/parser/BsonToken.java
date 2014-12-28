package com.github.light.bson.parser;

import com.fasterxml.jackson.core.JsonToken;

/**
 * Created by rob on 27-12-14.
 */
public enum BsonToken {
    NONE(null),
    START_OBJECT(JsonToken.START_OBJECT),
    END_OBJECT(JsonToken.END_OBJECT),
    START_ARRAY(JsonToken.START_ARRAY),
    END_ARRAY(JsonToken.END_ARRAY),

    FIELD_NAME(JsonToken.FIELD_NAME),

    VALUE_TRUE(JsonToken.VALUE_TRUE),
    VALUE_FALSE(JsonToken.VALUE_FALSE),

    VALUE_STRING(JsonToken.VALUE_STRING),

    VALUE_DATE_TIME(JsonToken.NOT_AVAILABLE),

    VALUE_NULL(JsonToken.VALUE_NULL),

    VALUE_NUMBER_INT(JsonToken.VALUE_NUMBER_INT),

    VALUE_NUMBER_FLOAT(JsonToken.VALUE_NUMBER_FLOAT),

    VALUE_BINARY(JsonToken.VALUE_STRING),



    ;

    public final JsonToken jsonToken;

    BsonToken(JsonToken jsonToken) {
        this.jsonToken = jsonToken;
    }
}
