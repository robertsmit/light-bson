package com.github.light.bson.write;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rob on 12-10-14.
 */
public class CachingBsonBuffer extends BsonBuffer {
    public static final int MAX_CACHE_SIZE = 5000;
    private static Map<String, byte[]> FIELD_CACHE = new HashMap<String, byte[]>(100);

    public CachingBsonBuffer(int size) {
        super(size);
    }

    @Override
    public BsonBuffer putField(String name, byte type) throws IOException {
        byte[] uftName = FIELD_CACHE.get(name);
        if (uftName == null) {
            uftName = name.getBytes(UTF8_CHARSET);
            if (FIELD_CACHE.size() > MAX_CACHE_SIZE) {
                FIELD_CACHE.clear();
            }
            FIELD_CACHE.put(name, uftName);
        }
        else {
            FIELD_CACHE.put(name, uftName);
        }
        return putField(uftName, type);
    }
}
