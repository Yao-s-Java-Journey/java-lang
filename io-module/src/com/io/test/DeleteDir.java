package com.io.test;

import java.io.File;

public class DeleteDir {
    public static void main(String[] args) {
        // deleteDirectory(new File(xxxxx));
    }

    public static void deleteDirectory(File dir) {
        // 1. 没有文件夹
        if (dir == null || !dir.exists()) return;

        // 2. 文件直接删除
        if (dir.isFile()) {
            dir.delete();
            return;
        };

        // 3. 文件夹存在
        File[] files = dir.listFiles();
        // 3.1 没有文件或无权限，退出
        if (files == null) return;
        // 3.2 空文件夹直接删除
        if (files.length == 0) {
            dir.delete();
            return;
        };
        // 3.3 遍历全部一级文件对象，进行删除
        for (File file : files) {
            if (file.isFile()) {
                file.delete();
            } else {
                deleteDirectory(file);
            }
        }

        // 4. 最后把自己删掉
        dir.delete();
    }
}
