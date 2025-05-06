package com.reflect.demo;

public class getClass {
    public static void main(String[] args) throws Exception {
        // 获取 Class 对象
        // 方式一：类名.class
        Class c1 = Student.class;
        System.out.println(c1);
        // 方式二：Class.forName(全类名)
        Class c2 = Class.forName("com.reflect.demo.Student");
        // 方式二：对象.getClass()
        Student s1 = new Student();
        Class c3 = s1.getClass();
    }
}
