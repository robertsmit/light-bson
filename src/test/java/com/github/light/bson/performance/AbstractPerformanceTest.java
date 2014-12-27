package com.github.light.bson.performance;

import com.fasterxml.jackson.core.JsonGenerator;
import com.github.light.bson.AbstractBsonTest;
import com.github.light.bson.util.JsonObjectRecipe;
import com.github.light.bson.util.JsonRecipe;
import com.github.light.bson.util.WriterExamples;
import org.junit.Before;

import java.io.IOException;

/**
 * Created by rob on 19-10-14.
 */
public class AbstractPerformanceTest extends AbstractBsonTest {

    @Before
    public void setUp() {

    }

    public JsonRecipe forBigPolymorphicObject() {
        return new JsonObjectRecipe() {
            @Override
            protected void writeBody(JsonGenerator generator) throws IOException {
                writeRecursiveObject(4, generator);
            }
        };
    }

    public void writeRecursiveObject(int i, JsonGenerator generator) throws IOException {
        generator.writeFieldName("recurObject");

        generator.writeStartObject();

        generator.writeFieldName("object");
        WriterExamples.forPolymorphicObject().write(generator);

        if (i > 0) {
            writeRecursiveObject(i - 1, generator);
        }

        generator.writeEndObject();
    }
}
