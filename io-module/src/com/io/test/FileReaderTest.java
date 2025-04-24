package com.io.test;

import java.io.FileReader;
import java.io.Reader;

public class FileReaderTest {
    public static void main(String[] args) {
        try (
                Reader reader = new FileReader("io-module\\src\\com\\io\\test\\data1.txt");
        ) {
            // 1. 读取一个字符，没有返回 -1
            // int c1 = reader.read();
            // System.out.println("c1 = " + c1);
            // int c2 = reader.read();
            // System.out.println("c2 = " + c2);

            // 2. 循环
            // int code;
            // while ((code = reader.read()) != -1) {
            //     System.out.print((char) code);
            // }

            // 3. 用字符数组去读取数据
            // char[] buffer = new char[3];
            // int len;
            // while ((len = reader.read(buffer)) != -1) {
            //     System.out.print(new String(buffer, 0, len));
            // }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
