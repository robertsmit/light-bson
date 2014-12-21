package com.github.light.bson.regression;

import com.github.light.bson.util.JsonFactoryWriter;
import com.github.light.bson.util.JsonReplayer;
import com.github.light.bson.util.JsonWriter;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by rob on 17-12-14.
 */
public class BsonParserRegressionTest extends AbstractRegressionTest {
    @Override
    protected void testRegression(JsonWriter writer) throws IOException {
        //System.out.println(new String(new GenerateAction(jackson, writer).generate(), BsonConstants.UTF8_CHARSET));
        byte[] original = new JsonFactoryWriter(undercouchBson4jackson, writer).generate();
        JsonReplayer replayer = new JsonReplayer(lightBson4Jackson);
        byte[] replayed = replayer.replay(original);
        Assert.assertEquals(bytesToHexDisplay(original), bytesToHexDisplay(replayed));
        Assert.assertTrue(Arrays.equals(original, replayed));
    }
}
