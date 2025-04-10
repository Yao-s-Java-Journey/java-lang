package com.oop.innerClass.jingtai;

public class People {
    private String name = "张三";
    private static double height;

    // 静态内部类
    public static class User {
        public void show() {
            // 可以访问外部类的静态成员
            System.out.println(height); // success

            // 不可以访问外部类的实例成员
//            System.out.println(name); // error
        }
    }
}
