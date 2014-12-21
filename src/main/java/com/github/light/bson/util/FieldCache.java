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
    private Map<ByteArrayWrapper, String> decodedFields = new ConcurrentHashMap<ByteArrayWrapper, String>(128);

    public byte[] getEncodeField(String decodedField) {
        byte[] encodedField = encodedFields.get(decodedField);
        if (encodedField == null) {
            encodedField = decodedField.getBytes(BsonConstants.UTF8_CHARSET);
            encodedFields.put(decodedField, encodedField);
        }
        return encodedField;
    }

    public String getDecodedField(byte[] encodedField) {
        ByteArrayWrapper key = new ByteArrayWrapper(encodedField);
        String decodedField = decodedFields.get(key);
        if (decodedField == null) {
            decodedField = new String(encodedField, BsonConstants.UTF8_CHARSET);
            decodedFields.put(key, decodedField);
        }
        return decodedField;
    }

    private static final class ByteArrayWrapper {
        private final byte[] data;

        public ByteArrayWrapper(byte[] data) {

            this.data = data;
        }

        @Override
        public boolean equals(Object other) {
            return Arrays.equals(data, ((ByteArrayWrapper) other).data);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(data);
        }
    }
}
