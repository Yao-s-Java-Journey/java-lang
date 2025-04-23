package com.io.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileOutputStreamTest {
    public static void main(String args[]) throws IOException {
        OutputStream out = new FileOutputStream("io-module\\src\\com\\io\\test\\data2.txt");

        // 1. 写字节数据进去
        out.write('a');
        out.write(98);
        out.write('徐'); // 只取一个字节，会导致乱码
    }
}
