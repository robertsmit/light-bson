package com.github.light.bson.parser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.util.Date;

/**
 * Created by rob on 18-12-14.
 */
public interface BsonParser {
    void close() throws IOException;

    /**
     * Method that can be called to get the name associated with
     * the current token: for {@link BsonToken#FIELD_NAME}s it will
     * be the same as what {@link #getText} returns;
     * for field values it will be preceding field name;
     * and for others (array values, root-level values) null.
     */
    String getCurrentName();

    /**
     * Accessor to find which token parser currently points to, if any;
     * NONE will be returned if none.
     * If return value is not NONE, data associated with the token
     * is available via other accessor methods.
     *
     * @return Type of the token this parser currently points to,
     *   if any: NONE before any tokens have been parser, and
     *   after end-of-input has been encountered, as well as
     *   if the current token has been explicitly cleared.
     */

    BsonToken getCurrentToken();

    /**
     * Numeric accessor that can be called for {@link BsonToken#VALUE_NUMBER_INT};
     * if so, it is equivalent to calling {@link #getLongValue}
     * and then casting; except for possible overflow/underflow
     * exception.
     */
    double getDoubleValue();

    /**
     * Numeric accessor that can be called when the current
     * token is of type {@link BsonToken#VALUE_NUMBER_INT} and
     * it can be expressed as a value of Java int primitive type.
     * It can also be called for {@link JsonToken#VALUE_NUMBER_FLOAT};
     * if so, it is equivalent to calling {@link #getDoubleValue}
     * and then casting; except for possible overflow/underflow
     * exception.
     */
    int getIntValue() throws JsonParseException;

    /**
     * Numeric accessor that can be called when the current
     * token is of type {@link BsonToken#VALUE_NUMBER_INT} and
     * it can be expressed as a Java long primitive type.
     * It can also be called for {@link JsonToken#VALUE_NUMBER_FLOAT};
     * if so, it is equivalent to calling {@link #getDoubleValue}
     * and then casting to int; except for possible overflow/underflow
     * exception.
     */
    long getLongValue() throws JsonParseException;

    Date getDateValue();

    /**
     * Method for accessing textual representation of the current token;
     * if no current token (before first call to {@link #nextToken}, or
     * after encountering end-of-input), returns null.
     * Method can be called for any token type.
     */
    String getText();

    /**
     * Method that will try to convert value of current token to a
     * {@link java.lang.String}.
     * If representation can not be converted to a String value (including structured types
     * like Objects and Arrays and null token), specified default value
     * will be returned; no exceptions are thrown.
     *
     * @since 2.1
     */
    String getValueAsString(String def);

    /**
     * Method that can be called to determine whether this parser
     * is closed or not. If it is closed, no new tokens can be
     * retrieved by calling {@link #nextToken} (and the underlying
     * stream may be closed). Closing may be due to an explicit
     * call to {@link #close} or because parser has encountered
     * end of input.
     */
    boolean isClosed();

    /**
     * Main iteration method, which will advance stream enough
     * to determine type of the next token, if any. If none
     * remaining (stream has no content other than possible
     * white space before ending), null will be returned.
     *
     * @return Next token from the stream, if any found, or null
     *   to indicate end-of-input
     */
    BsonToken nextToken() throws IOException;

    /**
     * Iteration method that will advance stream enough
     * to determine type of the next token that is a value type
     * (including Bson Array and Object start/end markers).
     * Or putAll another way, nextToken() will be called once,
     * and if {@link BsonToken#FIELD_NAME} is returned, another
     * time to get the value for the field.
     * Method is most useful for iterating over value entries
     * of JSON objects; field name will still be available
     * by calling {@link #getCurrentName} when parser points to
     * the value.
     *
     * @return Next non-field-name token from the stream, if any found,
     *   or null to indicate end-of-input (or, for non-blocking
     *   parsers, {@link JsonToken#NOT_AVAILABLE} if no tokens were
     *   available yet)
     */
    BsonToken nextValue() throws IOException;

    /**
     * Method that will skip all child tokens of an array or
     * object token that the parser currently points to,
     * iff stream points to
     * {@link BsonToken#START_OBJECT} or {@link BsonToken#START_ARRAY}.
     * If not, it will do nothing.
     * After skipping, stream will point to <b>matching</b>
     * {@link BsonToken#END_OBJECT} or {@link BsonToken#END_ARRAY}
     * (possibly skipping nested pairs of START/END OBJECT/ARRAY tokens
     * as well as value tokens).
     * The idea is that after calling this method, application
     * will call {@link #nextToken} to point to the next
     * available token, if any.
     */
    BsonParser skipChildren() throws IOException;
}
