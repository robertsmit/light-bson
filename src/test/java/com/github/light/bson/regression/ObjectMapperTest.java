package com.github.light.bson.regression;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.light.bson.AbstractBsonTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by rob on 27-12-14.
 */
public class ObjectMapperTest extends AbstractBsonTest {

    @Test
    public void testPojo() throws IOException {
        Pojo pojo = new Pojo(1);
        ObjectMapper mapper = new ObjectMapper(lightBson4Jackson);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        mapper.writeValue(out, pojo);
        byte[] objectBytes = out.toByteArray();

        System.out.println(bytesToHexDisplay(objectBytes));

        ByteArrayInputStream in = new ByteArrayInputStream(objectBytes);
        Pojo pojoCopy = mapper.readValue(in, Pojo.class);
        Assert.assertEquals(pojo, pojoCopy);
    }


    public static class Pojo {
        private long longValue;

        private Pojo() {

        }

        public Pojo(long longValue) {
            this.longValue = longValue;
        }

        public long getLongValue() {
            return longValue;
        }

        public void setLongValue(long longValue) {
            this.longValue = longValue;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Pojo && ((Pojo)obj).getLongValue() == longValue;
        }
    }
}
