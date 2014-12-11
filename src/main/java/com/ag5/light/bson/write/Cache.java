package com.ag5.light.bson.write;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by rob on 11-12-14.
 */
public class Cache {
    private Map<String, byte[]> encodedFields = new ConcurrentHashMap<String, byte[]>(128);

    public byte[] getEncodeField(String field) {
        byte[] encodedField = encodedFields.get(field);
        if (encodedField == null) {
            encodedField = field.getBytes(BsonConstants.UTF8_CHARSET);
            encodedFields.put(field, encodedField);
        }
        return encodedField;
    }
}
