package com.github.light.bson;

import com.fasterxml.jackson.core.JsonFactory;
import de.undercouch.bson4jackson.BsonFactory;

import java.util.Arrays;

/**
 * Created by rob on 11-12-14.
 */
public class AbstractBsonTest {
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    final protected static JsonFactory jackson = new JsonFactory();
    final protected static BsonFactory undercouchBson4jackson = new BsonFactory();
    final protected static JBsonFactory lightBson4Jackson = new JBsonFactory();

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

    protected String bytesToHexDisplay(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
