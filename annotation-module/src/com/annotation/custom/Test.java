package com.annotation.custom;

public class Test {
    @MyTest(name = "张三", money = 9.9, authors = {"李四", "王五"})
    @MyValue("value")
    public static void main(String[] args) {

    }
}
