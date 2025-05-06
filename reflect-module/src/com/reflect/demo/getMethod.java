package com.reflect.demo;

import org.junit.Test;

import java.lang.reflect.Method;

public class getMethod {
    @Test
    public void test() throws Exception {
        Class c1 = Student.class;

        // 获取全部方法
        Method[] methods = c1.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println("成员方法的名字：" + m.getName() + "，参数个数：" + m.getParameterCount());
        }

        // 获取单个方法（可以通过参数类型区分重载）
        Method eatMethod1 = c1.getDeclaredMethod("eat");
        Method eatMethod2 = c1.getDeclaredMethod("eat", String.class);

        // invoke 调用实例方法
        Student student = new Student("张三", 18, 99.9);
        Object result = eatMethod1.invoke(student);
        System.out.println(result);
        // 传递参数
        eatMethod2.invoke(student, "薯片");
        // 暴力反射
        Method eatMethod3 = c1.getDeclaredMethod("play", String.class);
        eatMethod3.setAccessible(true);
        eatMethod3.invoke(student, "唱跳rapper");
    }
}
