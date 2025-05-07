package com.annotation.myJunit;

import java.lang.reflect.Method;

// 模拟 Junit 测试框架, 自动执行有 @MyTest 修饰的方法
public class MyJunit {
    public static void main(String[] args) throws Exception {
        MyJunit unit = new MyJunit();
        Class c = MyJunit.class;
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyTest.class)) {
                method.invoke(unit);
            }
        }
    }

    public void test1() {
        System.out.println("==== test1 ====");
    }

    @MyTest
    public void test2() {
        System.out.println("==== test2 ====");
    }

    public void test3() {
        System.out.println("==== test3 ====");
    }

    @MyTest
    public void test4() {
        System.out.println("==== test4 ====");
    }
}
