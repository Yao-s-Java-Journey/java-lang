package com.io.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileInputStreamTest {
    public static void main(String[] args) throws IOException {
        // InputStream in = new java.io.FileInputStream(new File("io-module\\src\\com.io.test\\data1.txt"));
        InputStream in = new FileInputStream("io-module\\src\\com\\io\\test\\data1.txt");

        // 1.1 单次读取一个字节返回（如果没有字节可读，就返回 -1）
        // int b1 = in.read();
        // System.out.println(b1);
        // System.out.println((char) b1);

        // int b2 = in.read();
        // System.out.println(b2);
        // System.out.println((char) b2);

        // int b3 = in.read();
        // System.out.println(b3);
        // System.out.println((char) b3);

        // 1.2 单次循环读取
        // int index;
        // while ((index = in.read()) != -1) {
        //     System.out.print((char) index);
        // }

        // 2.1 单次读取一个字节数组的字节
        // 读取 "a89cd"
        // byte[] buf = new byte[3]; // 指定3个字节
        // int len1 = in.read(buf);
        // System.out.println("内容：" + new String(buf)); // a89
        // System.out.println("个数：" + len1); // 3

        // 再执行一次
        // int len2 = in.read(buf);
        // System.out.println("内容：" + new String(buf));
        // 结果是 cd9 而不是 cd，因为不会自动清空上一次的结果，而是读取多少覆盖多少。
        // 还剩2个，c 把 a 替换掉，d 把 8 替换掉，9 没有动
        // 第一次执行 │ a     │ 8     │ 9     │
        // 第二次执行 │ a ← c │ 8 ← d │ 9 不变 │
        //      结果 │ c     │ d     │ 9     │

        // System.out.println("个数：" + len2); // 2，因为只剩 2 个了

        // 再执行一次
        // int len3 = in.read(buf);
        // System.out.println("内容：" + new String(buf)); // 还是上一次的结果
        // System.out.println("个数：" + len3); // -1

        // 2.2 循环改进
        // byte[] buffer = new byte[3];
        // int len4;
        // while ((len4 = in.read(buffer)) != -1) {
        //     String str = new String(buffer, 0, len4); //
        //     System.out.print(str);
        // }

        // 3.1 一次读取完全部字节
        // File f = new File("io-module\\src\\com\\io\\test\\data1.txt");
        // long size = f.length();
        // byte[] buf = new byte[(int) size]; // 指定文件大小的读取长度，一次性读完
        // int len5 = in.read(buf);
        // System.out.println("内容：" + new String(buf));
        // System.out.println("个数：" + len5);

        // 3.2 直接调用 api
         byte[] buffer2 = in.readAllBytes();
         System.out.println("内容：" + new String(buffer2));
    }
}
