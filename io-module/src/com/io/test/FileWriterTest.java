package com.io.test;

import java.io.FileWriter;
import java.io.Writer;

public class FileWriterTest {
    public static void main(String[] args) {
        try (
                Writer writer = new FileWriter(
                        "io-module\\src\\com\\io\\test\\writer_out.txt",
                        true
                );
        ) {
            writer.write(97);
            writer.write("b");
            writer.write("\r\n");

            writer.write("abc我爱你中国def");
            writer.write("\r\n");

            writer.write("abc我爱你中国def", 3, 3);
            writer.write("\r\n");

            char[] chars = "abc我爱你中国def".toCharArray();
            writer.write(chars);
            writer.write("\r\n");

            writer.write(chars, 6, 2);
            writer.write("\r\n");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
