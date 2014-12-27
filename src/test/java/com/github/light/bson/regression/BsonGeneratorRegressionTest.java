package com.github.light.bson.regression;

import com.github.light.bson.generator.DefaultBsonGenerator;
import com.github.light.bson.generator.JBsonGenerator;
import com.github.light.bson.parser.BsonParser;
import com.github.light.bson.parser.BsonToken;
import com.github.light.bson.parser.DefaultBsonParser;
import com.github.light.bson.parser.JBsonParser;
import com.github.light.bson.util.BsonInputStream;
import com.github.light.bson.util.JsonFactoryWriter;
import com.github.light.bson.util.JsonWriter;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by rob on 12-10-14.
 */
public class BsonGeneratorRegressionTest extends AbstractRegressionTest {
    @Override
    protected void testRegression(JsonWriter writer) throws IOException {
        byte[] undercouch = new JsonFactoryWriter(undercouchBson4jackson, writer).generate();
        byte[] light = new JsonFactoryWriter(lightBson4Jackson, writer).generate();
        assertBsonEquals(undercouch, light);
    }



}
