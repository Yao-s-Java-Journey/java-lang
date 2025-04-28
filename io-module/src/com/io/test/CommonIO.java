package com.io.test;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CommonIO {
    public static void main(String[] args) throws IOException {
        // apache.commons.io 拷贝文件
        FileUtils.copyFile(new File("io-module\\src\\com\\io\\test\\data7.txt"), new File("io-module\\src\\com\\io\\test\\data7_apache.txt"));

        // JDK7 开始原生支持文件复制方法
        Files.copy(
                Path.of("io-module/src/com/io/test/data7.txt"),
                Path.of("io-module/src/com/io/test/data7_cp.txt")
        );
    }
}
