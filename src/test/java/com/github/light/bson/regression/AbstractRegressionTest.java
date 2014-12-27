package com.github.light.bson.regression;

import com.github.light.bson.AbstractBsonTest;
import com.github.light.bson.util.TestCookBook;
import com.github.light.bson.util.JsonRecipe;
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
        testRegression(TestCookBook.helloWorld());
    }

    @Test
    public void testArray() throws IOException {
        testRegression(TestCookBook.polymorphicArray());
    }

    @Test
    public void testIntegerArray() throws IOException {
        testRegression(TestCookBook.integerArrayInterval(-3000, 3000));
    }

    @Test
    public void testLongArray() throws IOException {
        testRegression(TestCookBook.longArray(-30000000000L, 100));
        testRegression(TestCookBook.longArray(30000000000L, 100));
    }

    @Test
    public void testEmptyArray() throws IOException {
        testRegression(TestCookBook.emptyArray());
    }

    protected abstract void testRegression(JsonRecipe writer) throws IOException;
}
