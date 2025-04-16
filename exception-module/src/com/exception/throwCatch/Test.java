package com.exception.throwCatch;

public class Test {
    public static void main(String[] args) {
        try {
            int res = divide(10, 0);
            System.out.println("计算结果: " + res);
        } catch (RuntimeException e) {
            System.out.println("捕获到异常");
            e.printStackTrace();
        }
        System.out.println("程序运行结束");

    }

    public static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Divide by zero");
        } else {
            return a / b;
        }
    }
}
