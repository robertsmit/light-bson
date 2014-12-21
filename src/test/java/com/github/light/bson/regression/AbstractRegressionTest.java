package com.github.light.bson.regression;

import com.github.light.bson.AbstractBsonTest;
import com.github.light.bson.util.JsonWriter;
import com.github.light.bson.util.WriterExamples;
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
        testRegression(WriterExamples.forHelloWorld());
    }

    @Test
    public void testArray() throws IOException {
        testRegression(WriterExamples.forPolymorphicArray());
    }

    @Test
    public void testIntegerArray() throws IOException {
        testRegression(WriterExamples.forIntegerArrayInterval(-3000, 3000));
    }

    @Test
    public void testLongArray() throws IOException {
        testRegression(WriterExamples.forLongArray(-30000000000L, 100));
        testRegression(WriterExamples.forLongArray(30000000000L, 100));
    }

    @Test
    public void testEmptyArray() throws IOException {
        testRegression(WriterExamples.forEmptyArray());
    }

    protected abstract void testRegression(JsonWriter writer) throws IOException;
}
