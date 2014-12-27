package com.github.light.bson.regression;

import com.github.light.bson.util.JsonFactoryWriter;
import com.github.light.bson.util.JsonRecipe;

import java.io.IOException;

/**
 * Created by rob on 12-10-14.
 */
public class BsonGeneratorRegressionTest extends AbstractRegressionTest {
    @Override
    protected void testRegression(JsonRecipe writer) throws IOException {
        byte[] undercouch = new JsonFactoryWriter(undercouchBson4jackson, writer).generate();
        byte[] light = new JsonFactoryWriter(lightBson4Jackson, writer).generate();
        assertBsonEquals(undercouch, light);
    }



}
