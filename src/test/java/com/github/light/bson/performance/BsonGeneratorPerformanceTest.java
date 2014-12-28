package com.github.light.bson.performance;

import com.fasterxml.jackson.core.JsonFactory;
import com.github.light.bson.util.JsonFactoryWrapper;
import com.github.light.bson.util.TestCookBook;
import com.github.light.bson.util.JsonReplayer;
import com.github.light.bson.util.JsonRecipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

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
        JsonFactoryWrapper undercouchWriter = new JsonFactoryWrapper(undercouchBson4jackson, writer);
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
        JsonRecipe writer = forBigPolymorphicObject();
        JsonFactoryWrapper lightWriter = new JsonFactoryWrapper(lightBson4Jackson, writer);
        JsonFactoryWrapper undercouchWriter = new JsonFactoryWrapper(undercouchBson4jackson, writer);

        assertBsonEquals(undercouchWriter.generate(), lightWriter.generate());
        int iterations = 8000;

        System.gc();
        long startMine = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            lightWriter.generate();
        }
        long durationMine = System.currentTimeMillis() - startMine;


        System.gc();
        long startOther = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            undercouchWriter.generate();
        }
        long durationOther = System.currentTimeMillis() - startOther;


        System.out.println("other---");
        System.out.println(durationOther);
        System.out.println("mine---");
        System.out.println(durationMine);
        System.out.println("perc---");
        long perc = (durationMine * 100) / durationOther;
        System.out.println(perc);
        Assert.assertTrue(perc < 70);
    }
}
