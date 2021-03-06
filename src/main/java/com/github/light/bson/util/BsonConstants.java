package com.github.light.bson.util;

import java.nio.charset.Charset;

/**
 * Created by rob on 12-10-14.
 */
public final class BsonConstants {


    private BsonConstants() {}

    public static final byte TERMINATOR = 0;

    public static final byte DOUBLE = 1;

    public static final byte STRING = 2;

    public static final byte OBJECT = 3;

    public static final byte ARRAY = 4;

    public static final byte BINARY = 5;

    public static final byte BOOLEAN = 8;

    public static final byte FALSE = 0;

    public static final byte TRUE = 1;

    public static final byte UTC_DATE_TIME = 9;

    public static final byte NULL = 10;

    public static final byte INT32 = 16;

    public static final byte INT64 = 18;

    public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    public static final byte BINARY_SUBTYPE_GENERIC = 0;
}

