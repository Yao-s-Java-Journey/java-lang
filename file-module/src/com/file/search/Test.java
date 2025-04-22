package com.file.search;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        File dir = new File("file-module");
        searchFile(dir, "aa");
    }

    /**
     * 递归文件搜索
     *
     * @param dir      被搜索的文件夹
     * @param fileName 被搜索的文件名
     */
    public static void searchFile(File dir, String fileName) {
        if (dir == null || !dir.exists() || dir.isFile()) return;

        File[] files = dir.listFiles();
        if (files == null || files.length == 0) return;
        for (File file : files) {
            if (file.isFile()) {
                if (file.getName().contains(fileName)) {
                    System.out.println("已查询到文件路径：" + file.getAbsolutePath());
                }
            } else {
                searchFile(file, fileName);
            }
        }
    }
}
