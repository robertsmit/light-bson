package com.github.light.bson.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by rob on 11-12-14.
 */
public class FieldCache {
    private Map<String, byte[]> encodedFields = new ConcurrentHashMap<String, byte[]>(128);
    private Map<byte[], String> decodedFields = new ConcurrentHashMap<byte[], String>(128);

    public byte[] getEncodeField(String decodedField) {
        byte[] encodedField = encodedFields.get(decodedField);
        if (encodedField == null) {
            encodedField = decodedField.getBytes(BsonConstants.UTF8_CHARSET);
            encodedFields.put(decodedField, encodedField);
            decodedFields.put(encodedField, decodedField);
        }
        return encodedField;
    }

    public String getDecodedField(byte[] encodedField) {
        String decodedField = decodedFields.get(encodedField);
        if (decodedField == null) {
            decodedField = new String(encodedField, BsonConstants.UTF8_CHARSET);
            encodedFields.put(decodedField, encodedField);
            decodedFields.put(encodedField, decodedField);
        }
        return decodedField;
    }
}
