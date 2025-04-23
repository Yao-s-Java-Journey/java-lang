package com.file.charset;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // 字符的编码与解码
        String info = "用户信息";

        // 编码
        byte[] bytes = info.getBytes("GBK");
        System.out.println(Arrays.toString(bytes));

        // 解码
        String res1 = new String(bytes);
        System.out.println(res1); // 乱码

        // 指定解码格式
        String res2 = new String(bytes, "GBK");
        System.out.println(res2);
    }
}
