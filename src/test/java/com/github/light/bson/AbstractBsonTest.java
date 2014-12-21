package com.github.light.bson;

import com.fasterxml.jackson.core.JsonFactory;
import de.undercouch.bson4jackson.BsonFactory;

import java.util.Arrays;

/**
 * Created by rob on 11-12-14.
 */
public class AbstractBsonTest {
    protected static JsonFactory jackson = new JsonFactory();
    protected static BsonFactory undercouchBson4jackson = new BsonFactory();
    protected static JBsonGeneratorFactory lightBson4Jackson = new JBsonGeneratorFactory();

    protected void assertBsonEquals(byte[] expected, byte[] actual) {
        boolean condition = Arrays.equals(expected, actual);
        if (!condition) {
            System.out.println("__expected__");
            System.out.println(bytesToHexDisplay(expected));
            System.out.println("__actual__");
            System.out.println(bytesToHexDisplay(actual));
            throw new AssertionError();
        }
    }

    protected String bytesToHexDisplay(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : in) {
            if (b >= 65) {
                builder.append((char)b);
            }
            else {
                builder.append("|");
                builder.append(String.format("x%02X", b));
            }
        }
        return builder.toString();
    }
}
