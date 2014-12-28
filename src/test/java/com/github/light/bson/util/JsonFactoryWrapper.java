package com.github.light.bson.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by rob on 21-12-14.
 */
public class JsonFactoryWrapper {
    private final JsonRecipe recipe;
    private JsonFactory factory;

    public JsonFactoryWrapper(JsonFactory factory, JsonRecipe recipe) {
        this.factory = factory;
        this.recipe = recipe;
    }

    public byte[] generate() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        write(out);
        return out.toByteArray();
    }

    public void write(OutputStream out) throws IOException {
        JsonGenerator generator = factory.createGenerator(out);
        write(generator);
        generator.close();
    }

    private void write(JsonGenerator generator) throws IOException {
        recipe.write(generator);
    }

}
