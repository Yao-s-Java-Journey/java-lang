package com.io.test;

import java.io.*;

public class InputStreamReaderTest {
    public static void main(String[] args) {
        try (
                // 1. 得到GBK文件的原始字节输入流
                InputStream isr = new FileInputStream("io-module\\src\\com\\io\\test\\data4_gbk.txt");
                // 2. 通过字符输入转换流把原始字节输入流按照指定编码格式转成字符输入流
                Reader reader = new InputStreamReader(isr, "GBK");
                // 3. 再使用高级缓冲输入流包装
                BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
