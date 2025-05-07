package com.annotation.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS) //
@Target({ElementType.TYPE, ElementType.METHOD}) // MyTest 注解只可以修饰类、方法
public @interface MyTest {
}
