package com.github.light.bson.util;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by rob on 11-12-14.
 */
public class FieldCache {
    private Map<String, byte[]> encodedFields = new ConcurrentHashMap<String, byte[]>(128);

    public byte[] getEncodeField(String decodedField) {
        byte[] encodedField = encodedFields.get(decodedField);
        if (encodedField == null) {
            encodedField = decodedField.getBytes(BsonConstants.UTF8_CHARSET);
            encodedFields.put(decodedField, encodedField);
        }
        return encodedField;
    }
}
