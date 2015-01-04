package com.github.light.bson.performance;

import com.fasterxml.jackson.core.JsonFactory;
import com.github.light.bson.util.JsonFactoryWriter;
import com.github.light.bson.util.TestCookBook;
import com.github.light.bson.util.JsonReplayer;
import com.github.light.bson.util.JsonRecipe;
import com.google.common.io.ByteStreams;
import de.undercouch.bson4jackson.BsonGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by rob on 12-10-14.
 */
public class BsonGeneratorPerformanceTest extends AbstractPerformanceTest {

    @Before
    public void setUp() {
        super.setUp();
        System.gc();
    }

    @Test
    public void testReplay() throws IOException, InterruptedException {
        JsonRecipe writer = TestCookBook.simpleObject();
        JsonFactoryWriter undercouchWriter = new JsonFactoryWriter(undercouchBson4jackson, writer);
        byte[] input = undercouchWriter.generate();

        long durationOther = Math.min(testReplay(input, undercouchBson4jackson), testReplay(input, undercouchBson4jackson));

        long durationMine = Math.min(testReplay(input, lightBson4Jackson), testReplay(input, lightBson4Jackson));

        System.out.println("other---");
        System.out.println(durationOther);
        System.out.println("mine---");
        System.out.println(durationMine);
        System.out.println("perc---");
        long perc = (durationMine * 100) / durationOther;
        System.out.println(perc);
        Assert.assertTrue(perc < 100);
    }

    public long testReplay(byte[] input, JsonFactory factory) throws IOException, InterruptedException {
        JsonReplayer replayer = new JsonReplayer(factory);
        assertBsonEquals(input, replayer.replay(factory.createParser(input)));

        int iterations = 110000;
        replayer.replay(lightBson4Jackson.createParser(input));
        System.gc();
        long startMine = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            replayer.replay(lightBson4Jackson.createParser(input));
        }
        return System.currentTimeMillis() - startMine;
    }

    @Test
    public void testWriting() throws IOException {
        testWriting(undercouchBson4jackson, "undercouch", lightBson4Jackson, "light");
    }

    @Test
    public void testWritingStreaming() throws IOException {
        lightBson4Jackson.enableStreaming(true);
        undercouchBson4jackson.enable(BsonGenerator.Feature.ENABLE_STREAMING);
        testWriting(undercouchBson4jackson, "undercouch", lightBson4Jackson, "light");
    }



    public void testWriting(JsonFactory one, String labelOne,  JsonFactory two, String labelTwo) throws IOException {
        JsonRecipe recipe = forBigPolymorphicObject();
        JsonFactoryWriter oneWrapper = new JsonFactoryWriter(one, recipe);
        JsonFactoryWriter twoWrapper = new JsonFactoryWriter(two, recipe);

        assertBsonEquals(oneWrapper.generate(), twoWrapper.generate());
        int iterations = 8000;

        long durationTwo = measure(twoWrapper, iterations);

        long durationOne = measure(oneWrapper, iterations);

        System.out.println(labelOne + "---");
        System.out.println(durationOne);
        System.out.println(labelTwo + "---");
        System.out.println(durationTwo);
        System.out.println("perc---");
        long perc = (durationTwo * 100) / durationOne;
        System.out.println(perc);
        Assert.assertTrue(perc < 70);
    }

    public long measure(JsonFactoryWriter w, int iterations) throws IOException {
        OutputStream out = ByteStreams.nullOutputStream();
        System.gc();
        long start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            w.write(out);
        }
        return System.currentTimeMillis() - start;
    }
}
