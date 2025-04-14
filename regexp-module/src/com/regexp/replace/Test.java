package com.regexp.replace;

public class Test {
    public static void main(String[] args) {
        String str = "古力娜扎asd12迪丽热巴gdf241马尔扎哈88986ui卡尔扎巴";

        // 替换
        String res = str.replaceAll("\\w+", "-");
        System.out.println(res);
        System.out.println("\n-----------------\n");

        // 分割
        String[] names = str.split("\\w+");
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
    }
}
