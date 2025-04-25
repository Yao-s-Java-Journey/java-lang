package com.io.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class BufferedWriterTest {
    public static void main(String[] args) {
        try (
                Writer fw = new FileWriter("io-module\\src\\com\\io\\test\\data3_cp.txt");
                BufferedWriter bw = new BufferedWriter(fw);
        ) {
            bw.write(97);
            bw.write("b");
            bw.newLine();

            bw.write("abc我爱你中国def");
            bw.newLine();

            bw.write("abc我爱你中国def", 3, 3);
            bw.newLine();

            char[] chars = "abc我爱你中国def".toCharArray();
            bw.write(chars);
            bw.newLine();

            bw.write(chars, 6, 2);
            bw.newLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
