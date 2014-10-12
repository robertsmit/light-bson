package com.github.light;

import java.io.IOException;

/**
 * Created by rob on 12-10-14.
 */
public interface Writer<Self> {
    Self field(String name) throws IOException;
    Self writeString(String value) throws IOException;
    Self startArray() throws IOException;
    Self endArray() throws IOException;
    Self startObject() throws IOException;
    Self endObject() throws IOException;
    Self writeBoolean(boolean value) throws IOException;
    Self writeNull() throws IOException;
    Self writeNumber(short value) throws IOException;
    Self writeNumber(double value) throws IOException;
    Self writeNumber(int value) throws IOException;
    Self writeNumber(long value) throws IOException;
}
