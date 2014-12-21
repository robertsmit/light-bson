package com.github.light.bson.generator.state;

import com.github.light.bson.util.BsonOutputStream;

/**
 * Created by rob on 20-10-14.
 */
public class StartWriteState extends WriteState {
    private BsonOutputStream out;

    public StartWriteState(BsonOutputStream out) {
        this.out = out;
    }
}
