package com.github.light.bson.regression;

import com.github.light.bson.util.JsonFactoryWrapper;
import com.github.light.bson.util.JsonRecipe;
import com.github.light.bson.util.TestCookBook;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by rob on 12-10-14.
 */
public class BsonGeneratorRegressionTest extends AbstractRegressionTest {
    @Override
    protected void testRegression(JsonRecipe writer) throws IOException {
        byte[] undercouch = new JsonFactoryWrapper(undercouchBson4jackson, writer).generate();
        byte[] light = new JsonFactoryWrapper(lightBson4Jackson, writer).generate();
        assertBsonEquals(undercouch, light);
    }

    @Test
    public void testByteArray() throws IOException {
        testRegression(TestCookBook.binaryData(new byte[] { -1, 2, 3, -128, 127, 3, 45, 12 }));
    }
}
