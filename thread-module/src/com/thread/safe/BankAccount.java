package com.thread.safe;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class BankAccount {
//     private String cardNo;
//     private double balance;
//
//     // 取款
//     // public void withdraw(double money) {
//     // 加锁方式二：synchronized 同步方法
//     public synchronized void withdraw(double money) {
//         String name = Thread.currentThread().getName();
//
//         // 安全问题：
//         // if (balance >= money) {
//         //     System.out.println(name + "取款成功");
//         //     balance -= money;
//         //     System.out.println(name + "取款后，账户余额： " + balance);
//         // } else {
//         //     System.out.println("余额不足！");
//         // }
//
//         // 加锁方式一：synchronized 同步代码块
//         // 实例方法使用 this，静态方法使用 类名.class
//         // synchronized (this) {
//         //     if (balance >= money) {
//         //         System.out.println(name + "取款成功");
//         //         this.balance -= money;
//         //         System.out.println(name + "取款后，账户余额： " + balance);
//         //     } else {
//         //         System.out.println("余额不足！");
//         //     }
//         // }
//     }
// }

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 加锁方式三：Lock 锁
@Data
@NoArgsConstructor
public class BankAccount {
    private String cardNo;
    private double balance;
    private final Lock lk = new ReentrantLock();

    public BankAccount(String cardNo, double balance) {
        this.cardNo = cardNo;
        this.balance = balance;
    }

    public void withdraw(double money) {
        String name = Thread.currentThread().getName();

        lk.lock(); // 加锁
        try {
            if (balance >= money) {
                System.out.println(name + "取款成功");
                balance -= money;
                System.out.println(name + "取款后，账户余额： " + balance);
            } else {
                System.out.println("余额不足！");
            }
        } finally {
            lk.unlock(); // 解锁
        }
    }
}