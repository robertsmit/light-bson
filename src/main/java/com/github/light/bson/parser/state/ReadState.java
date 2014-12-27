package com.github.light.bson.parser.state;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonToken;
import com.github.light.bson.parser.BsonToken;
import com.github.light.bson.util.BsonInputStream;

import java.io.IOException;
import java.util.Date;

/**
 * Created by rob on 18-12-14.
 */
public interface ReadState {
    String getCurrentName();

    BsonToken getCurrentToken();

    Date getDateValue();

    double getDoubleValue();

    int getIntValue() throws JsonParseException;

    long getLongValue() throws JsonParseException;

    String getText();

    String getValueAsString(String def);

    boolean hasNext();

    ReadState skipChildren(BsonInputStream in) throws IOException;

    ReadState toValueContext(BsonInputStream in) throws IOException;

    ReadState next(BsonInputStream in) throws IOException;

    ReadState getParent();
}
