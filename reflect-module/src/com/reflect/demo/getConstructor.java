package com.reflect.demo;

import java.lang.reflect.Constructor;

public class getConstructor {
    public static void main(String[] args)  throws Exception {
        Class c1 = Student.class;

        // 获取全部构造器
        Constructor[] constructors = c1.getConstructors();
        for (Constructor con : constructors) {
            System.out.println("构造器：" + con.getName() + "，参数个数：" + con.getParameterCount());
        }

        // 获取某个构造器
        Constructor constructor1 = c1.getDeclaredConstructor(); // 获取无参构造器
        Constructor constructor2 = c1.getDeclaredConstructor(String.class, int.class, double.class); // 获取指定参数类型的构造器
        Constructor constructor3 = c1.getDeclaredConstructor(String.class, int.class); // 获取指定参数类型的构造器（私有也能获取）

        // 使用构造器创建实例对象
        Student s2 = (Student) constructor1.newInstance();
        Student s3 = (Student) constructor2.newInstance("张三", 18, 99);

        // 暴力反射，强行使用私有构造器
        constructor3.setAccessible(true); // 禁止访问权限检查
        Student s4 = (Student) constructor3.newInstance("张三", 18);
    }
}
