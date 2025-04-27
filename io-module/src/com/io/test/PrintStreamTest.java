package com.io.test;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamTest {
    public static void main(String[] args) {
        try (
                PrintStream ps = new PrintStream(new FileOutputStream("io-module\\src\\com\\io\\test\\info.txt", true));
        ) {
            System.setOut(ps);
            System.out.println("将系统打印的输出语句从控制台重定向到指定的文件中");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}