package com.reflect.demo;

import org.junit.Test;

import java.lang.reflect.Field;

public class getProperty {
    @Test
    public void test() throws Exception {
        Class c1 = Student.class;

        // 获取全部成员变量
        Field[] fields = c1.getDeclaredFields();
        for (Field f : fields) {
            System.out.println("成员变量的类型：" + f.getType() + "，成员变量的名字" + f.getName());
        }

        // 定位某个成员变量
        Field nameField = c1.getDeclaredField("name");
        Student student = new Student();
        nameField.setAccessible(true);
        nameField.set(student, "张三");
        String name = (String) nameField.get(student);
        System.out.println(name);
    }
}
