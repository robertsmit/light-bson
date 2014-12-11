package com.github.light.bson.performance;

import com.ag5.light.bson.jackson.BsonGeneratorFactory;
import com.github.light.bson.AbstractBsonTest;
import de.undercouch.bson4jackson.BsonFactory;
import org.junit.Before;

/**
 * Created by rob on 19-10-14.
 */
public class AbstractPerformanceTest extends AbstractBsonTest {
    protected static BsonFactory bson4jackson = new BsonFactory();
    protected static BsonGeneratorFactory ag5Bson = new BsonGeneratorFactory();

    @Before
    public void setUp() {

    }
}
