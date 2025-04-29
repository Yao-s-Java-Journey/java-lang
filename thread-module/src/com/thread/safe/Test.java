package com.thread.safe;

public class Test {
    public static void main(String[] args) {
        // 模拟线程安全问题
        BankAccount account = new BankAccount("88888888", 100.0);

        // 两个人同时去一个账户取钱
        DrawThread t1 = new DrawThread("小明", account);
        t1.start();
        DrawThread t2 = new DrawThread("小红", account);
        t2.start();
    }
}
