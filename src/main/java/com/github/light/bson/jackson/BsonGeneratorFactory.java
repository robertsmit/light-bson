package com.github.light.bson.jackson;

import com.github.light.bson.util.GrowingByteBuffer;
import com.github.light.bson.write.BsonStream;
import com.github.light.bson.write.Cache;
import com.github.light.bson.write.DefaultBsonWriter;

import java.io.OutputStream;

/**
 * Created by rob on 11-12-14.
 */
public class BsonGeneratorFactory {
    private GrowingByteBuffer sharedBuffer;
    private Cache cache;

    public BsonGeneratorFactory() {
        cache = new Cache();
    }

    public BsonGenerator createGenerator(OutputStream out) {
        return new BsonGenerator(new DefaultBsonWriter(new BsonStream(out), cache));
    }

    public BsonGenerator createUnsafeGenerator(OutputStream out) {
        if (sharedBuffer == null) {
            sharedBuffer = new GrowingByteBuffer(2048);
        }
        return new BsonGenerator(new DefaultBsonWriter(new BsonStream(out, sharedBuffer), cache));
    }
}
