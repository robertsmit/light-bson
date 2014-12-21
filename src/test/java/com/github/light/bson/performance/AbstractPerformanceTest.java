package com.github.light.bson.performance;

import com.fasterxml.jackson.core.JsonGenerator;
import com.github.light.bson.AbstractBsonTest;
import com.github.light.bson.util.JsonObjectWriter;
import com.github.light.bson.util.JsonWriter;
import com.github.light.bson.util.JsonWriterUtils;
import com.sun.xml.internal.stream.writers.WriterUtility;
import org.junit.Before;

import java.io.IOException;

/**
 * Created by rob on 19-10-14.
 */
public class AbstractPerformanceTest extends AbstractBsonTest {

    @Before
    public void setUp() {

    }

    public JsonWriter forBigPolymorphicObject() {
        return new JsonObjectWriter() {
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
        JsonWriterUtils.forPolymorphicObject().write(generator);

        if (i > 0) {
            writeRecursiveObject(i - 1, generator);
        }

        generator.writeEndObject();
    }
}
