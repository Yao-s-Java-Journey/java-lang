package com.annotation.parse;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

public class DemoTest {
    @Test
    public void run() throws Exception {
        Class demo = Demo.class;
        // 判断当前类上是否陈列该注解
        if (demo.isAnnotationPresent(MyTest.class)) {
            // 得到注解对象
            MyTest annotation = (MyTest) demo.getDeclaredAnnotation(MyTest.class);
            System.out.println(annotation.value());
            System.out.println(annotation.aaa());
            System.out.println(Arrays.toString(annotation.bbb()));
        }

        // 判断方法上是否陈列该注解
        Method method = demo.getDeclaredMethod("handle");
        if (method.isAnnotationPresent(MyTest.class)) {
            // 得到注解对象
            MyTest annotation = (MyTest) method.getDeclaredAnnotation(MyTest.class);
            System.out.println(annotation.value());
            System.out.println(annotation.aaa());
            System.out.println(Arrays.toString(annotation.bbb()));
        }
    }
}

@MyTest(value = "类上的注解", bbb = {"A", "B", "C"})
class Demo {
    @MyTest(value = "方法上的注解", aaa = 88, bbb = {"a", "b", "c"})
    public void handle() {
    }
}