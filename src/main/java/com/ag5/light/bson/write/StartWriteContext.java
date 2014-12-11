package com.ag5.light.bson.write;

/**
 * Created by rob on 20-10-14.
 */
public class StartWriteContext extends WriteContext {
    private BsonStream out;

    public StartWriteContext(BsonStream out) {
        this.out = out;
    }

    @Override
    public BsonStream getOut() {
        return out;
    }
}
