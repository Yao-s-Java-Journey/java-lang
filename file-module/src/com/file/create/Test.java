package com.file.create;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        // 1. 创建文件
        // File f1 = new File("E:\\user\\aa.jpg");
        // File f2 = new File("E:/user/aa.jpg");
        // File f3 = new File("E:" + File.separator + "user" + File.separator + "aa.jpg"";
        // f1.length(); // 字节个数

        // 2. File 也可以代表文件夹
        File f4 = new File("E:\\sonic");
        System.out.println(f4.length()); // 拿到的是文件夹本身的大小，不是里面所有文件的的大小

        // 3. 文件路径可以不存在
        File f5 = new File("E:\\nodata");

        // 4. 支持绝对路径和相对路径
        // 相对路径默认相对到工程目录下寻找
        // 一般用来好项目中的资源文件
        File f6 = new File("file-module/src/com.file.create/aa.jpg");
        System.out.println(f6.getAbsolutePath());
    }
}
