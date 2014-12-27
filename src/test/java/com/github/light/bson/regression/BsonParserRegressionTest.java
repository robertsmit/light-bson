package com.github.light.bson.regression;

import com.github.light.bson.generator.JBsonGenerator;
import com.github.light.bson.parser.BsonParser;
import com.github.light.bson.parser.BsonToken;
import com.github.light.bson.parser.DefaultBsonParser;
import com.github.light.bson.util.BsonInputStream;
import com.github.light.bson.util.JsonFactoryWriter;
import com.github.light.bson.util.JsonReplayer;
import com.github.light.bson.util.JsonWriter;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by rob on 17-12-14.
 */
public class BsonParserRegressionTest extends AbstractRegressionTest {
    @Override
    protected void testRegression(JsonWriter writer) throws IOException {
        byte[] original = new JsonFactoryWriter(undercouchBson4jackson, writer).generate();
        JsonReplayer replayer = new JsonReplayer(lightBson4Jackson);
        byte[] replayed = replayer.replay(original);
        Assert.assertEquals(bytesToHexDisplay(original), bytesToHexDisplay(replayed));
        Assert.assertTrue(Arrays.equals(original, replayed));
    }

    @Test
    public void testDateTime() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        JBsonGenerator generator = lightBson4Jackson.createGenerator(out);
        generator.writeStartObject();
        Date value = new Date();
        long valueLong = System.currentTimeMillis();
        generator.writeFieldName("time1");
        generator.writeDateTime(value);
        generator.writeFieldName("time2");
        generator.writeDateTime(valueLong);
        generator.writeFieldName("long");
        generator.writeNumber(valueLong);
        generator.writeEndObject();
        generator.close();

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        BsonParser parser = new DefaultBsonParser(new BsonInputStream(in));
        Assert.assertEquals(BsonToken.START_OBJECT, parser.nextToken());
        Assert.assertEquals(BsonToken.FIELD_NAME, parser.nextToken());
        Assert.assertEquals("time1", parser.getText());
        Assert.assertEquals(BsonToken.VALUE_DATE_TIME, parser.nextToken());
        Assert.assertEquals(value, parser.getDateValue());
        Assert.assertEquals(value.getTime(), parser.getLongValue());
        Assert.assertEquals(BsonToken.VALUE_DATE_TIME, parser.nextValue());
        Assert.assertEquals("time2", parser.getCurrentName());
        Assert.assertEquals(new Date(valueLong), parser.getDateValue());
        Assert.assertEquals(valueLong, parser.getLongValue());
        Assert.assertEquals(BsonToken.VALUE_NUMBER_INT, parser.nextValue());
        Assert.assertEquals("long", parser.getCurrentName());
        Assert.assertEquals(new Date(valueLong), parser.getDateValue());
        Assert.assertEquals(valueLong, parser.getLongValue());
        Assert.assertEquals(BsonToken.END_OBJECT, parser.nextToken());
        Assert.assertEquals(BsonToken.NONE, parser.nextToken());

        parser.close();
    }
}
