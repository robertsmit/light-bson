package com.github.light.bson.performance;

import com.fasterxml.jackson.core.JsonFactory;
import com.github.light.bson.util.JsonFactoryWriter;
import com.github.light.bson.util.JsonReplayer;
import com.github.light.bson.util.JsonWriter;
import com.github.light.bson.util.WriterExamples;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

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
    public void testWriting() throws IOException {
        JsonWriter writer = forBigPolymorphicObject();
        JsonFactoryWriter lightWriter = new JsonFactoryWriter(lightBson4Jackson, writer);
        JsonFactoryWriter undercouchWriter = new JsonFactoryWriter(undercouchBson4jackson, writer);

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

    @Test
    public void testReplay() throws IOException, InterruptedException {
        JsonWriter writer = WriterExamples.forSimpleObject();
        JsonFactoryWriter undercouchWriter = new JsonFactoryWriter(undercouchBson4jackson, writer);
        byte[] input = undercouchWriter.generate();

        JsonReplayer replayer = new JsonReplayer(lightBson4Jackson);
        assertBsonEquals(input, replayer.replay(lightBson4Jackson.createParser(input)));
        assertBsonEquals(input, replayer.replay(undercouchBson4jackson.createParser(input)));

        int iterations = 100000;


        replayer.replay(undercouchBson4jackson.createParser(input));
        System.gc();
        Thread.sleep(1000);
        System.gc();
        long startOther = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            replayer.replay(undercouchBson4jackson.createParser(input));
        }
        long durationOther = System.currentTimeMillis() - startOther;

        replayer.replay(lightBson4Jackson.createParser(input));
        System.gc();
        Thread.sleep(1000);
        System.gc();
        long startMine = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            replayer.replay(lightBson4Jackson.createParser(input));
        }
        long durationMine = System.currentTimeMillis() - startMine;



        System.out.println("other---");
        System.out.println(durationOther);
        System.out.println("mine---");
        System.out.println(durationMine);
        System.out.println("perc---");
        long perc = (durationMine * 100) / durationOther;
        System.out.println(perc);
        Assert.assertTrue(perc < 100);

    }


    private JsonFactoryWriter writeFor(JsonFactory factory) throws IOException {
        return new JsonFactoryWriter(factory, forBigPolymorphicObject());
    }
}
