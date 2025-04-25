package com.io.test;

import java.io.*;

public class OutputStreamWriterTest {
    public static void main(String[] args) {
        try(
                OutputStream fos = new FileOutputStream("io-module\\src\\com\\io\\test\\data4_gbk_cp.txt");
                Writer osw = new OutputStreamWriter(fos, "GBK");
                BufferedWriter bos = new BufferedWriter(osw);
        ) {
            bos.write("曲木为直终必弯，");
            bos.newLine();
            bos.write("养狼当犬看家难。");
            bos.newLine();
            bos.write("墨染鸬鹚黑不久，");
            bos.newLine();
            bos.write("粉刷乌鸦白不坚。");
            bos.newLine();
            bos.write("蜜饯黄莲终须苦，");
            bos.newLine();
            bos.write("强摘瓜果不得甜。");
            bos.newLine();
            bos.write("好事总得善人做，");
            bos.newLine();
            bos.write("哪有凡人做神仙！");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
