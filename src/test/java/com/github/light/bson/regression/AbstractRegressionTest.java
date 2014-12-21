package com.github.light.bson.regression;

import com.github.light.bson.AbstractBsonTest;
import com.github.light.bson.util.JsonWriter;
import com.github.light.bson.util.JsonWriterUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by rob on 19-10-14.
 */
public abstract class AbstractRegressionTest extends AbstractBsonTest {

    /**
     @see <a href="http://bsonspec.org/faq.html" />
     */
    @Test
    public void testExampleHelloWorld() throws IOException {
        testRegression(JsonWriterUtils.forHelloWorld());
    }

    @Test
    public void testArray() throws IOException {
        testRegression(JsonWriterUtils.forPolymorphicArray());
    }

    @Test
    public void testIntegerArray() throws IOException {
        testRegression(JsonWriterUtils.forIntegerArrayInterval(-3000, 3000));
    }

    @Test
    public void testLongArray() throws IOException {
        testRegression(JsonWriterUtils.forLongArray(-30000000000L, 100));
        testRegression(JsonWriterUtils.forLongArray(30000000000L, 100));
    }

    @Test
    public void testEmptyArray() throws IOException {
        testRegression(JsonWriterUtils.forEmptyArray());
    }

    protected abstract void testRegression(JsonWriter writer) throws IOException;
}
