package com.annotation.custom;

// 自定义注解
public @interface MyTest {
    String name();
    double money() default 99.9;
    String[] authors();
}
