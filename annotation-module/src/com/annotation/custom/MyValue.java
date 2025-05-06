package com.annotation.custom;

public @interface MyValue {
    String value();
    int age() default 0;
}
