package com.github.light.bson.parser.state;

/**
 * Created by rob on 19-12-14.
 */
public class EndFieldReadState extends InnerReadState {
    private String field;

    public EndFieldReadState(String field, ReadState parent) {
        super(parent);
        this.field = field;
    }

    @Override
    public String getCurrentName() {
        return field;
    }
}
