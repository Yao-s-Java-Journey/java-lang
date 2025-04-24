package com.io.test;

import java.io.*;

public class FileOutputStreamTest {
    public static void main(String args[]) throws IOException {
        OutputStream out = new FileOutputStream(
                "io-module\\src\\com\\io\\test\\data2.txt",
                true
        );

        // 1. 写字节数据进去
        out.write('a');
        out.write(98);
        // out.write('徐'); // 只取一个字节，会导致乱码
        out.write("\r\n".getBytes());

        // 写一个字节数组进去
        byte[] bytes = "cdef我尼玛".getBytes();
        out.write(bytes);
        out.write("\r\n".getBytes());
        out.write(bytes, 4, 3 * 3); // 从 4 号索引位置开始写入，总共写9个字节的长度（这里有3个汉字，一个汉字3个字节）

        // 关闭流
        out.close();

        System.out.println("-------------------------");

        // 文件的复制
        copyFile(
                "io-module\\src\\com\\io\\test\\data2.txt",
                "io-module\\src\\com\\io\\test\\data2_cp.txt"
        );
    }

    /**
     * 复制文件
     *
     * @param inputPathName  源文件地址
     * @param outputPathName 目标文件地址
     */
    public static void copyFile(String inputPathName, String outputPathName) {
//        InputStream input = null;
//        OutputStream output = null;
//        try {
//            // 创建字节输入流管道与源文件连通
//            input = new FileInputStream(inputPathName);
//            // 创建字节输出流管道与目标文件连通
//            output = new FileOutputStream(outputPathName);
//
//            // 准备一个字节数组
//            byte[] buffer = new byte[1024];
//
//            // 转移数据
//            int length;
//            while ((length = input.read(buffer)) > 0) {
//                output.write(buffer, 0, length);
//            }
//            System.out.println("Copied");
//
//        } catch(Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 手动释放资源
//            try {
//                if(input != null) input.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            try {
//                if(output != null) output.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }

        // JDK 8 后使用 try-with-resource 释放资源
        try (
                // try 中只能放置资源对象，用完会自动调用 close 方法释放资源
                // 创建字节输入流管道与源文件连通
                InputStream input = new FileInputStream(inputPathName);
                // 创建字节输出流管道与目标文件连通
                OutputStream output = new FileOutputStream(outputPathName);
        ) {
            // 准备一个字节数组
            byte[] buffer = new byte[1024];

            // 转移数据
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            System.out.println("Copied");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
