package com.github.light.bson.performance;

import com.fasterxml.jackson.core.JsonGenerator;
import com.github.light.bson.AbstractBsonTest;
import com.github.light.bson.util.TestCookBook;
import com.github.light.bson.util.ObjectJsonRecipe;
import com.github.light.bson.util.JsonRecipe;
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
        return new ObjectJsonRecipe() {
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
        TestCookBook.polymorphicObject().write(generator);

        if (i > 0) {
            writeRecursiveObject(i - 1, generator);
        }

        generator.writeEndObject();
    }
}
