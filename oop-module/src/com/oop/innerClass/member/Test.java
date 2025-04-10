package com.oop.innerClass.member;

public class Test {
    public static void main(String[] args) {
        // 创建成员内部类实例对象
        People.User user = new People().new User();
        user.show();
    }
}
