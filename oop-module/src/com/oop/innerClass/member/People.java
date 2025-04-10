package com.oop.innerClass.member;

public class People {
    private String name = "张三";

    // 成员内部类
    public class User {
        private String name = "李四";

        public void show() {
            String name = "王五";
            System.out.println(name); // 王五
            System.out.println(this.name); // 李四
            System.out.println(People.this.name); // 张三
        }
    }
}
