package com.exception.custom;

public class Test {
    public static void main(String[] args) {
        // 捕获运行异常
        try {
            setAge(200);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 捕获编译异常
        try {
            setName(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setAge(int age) {
        if (age < 1 || age > 100) {
            throw new CustomRuntimeException("非法年龄");
        }
        System.out.println("年龄合法");
    }

    public static void setName(String name) throws CustomCompileException {
        if (name == null) {
            throw new CustomCompileException("非法姓名");
        }
        System.out.println("名字合法");
    }
}
