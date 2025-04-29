package com.thread.safe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    private String cardNo;
    private double balance;

    // 取款
    public void withdraw(double money) {
        String name = Thread.currentThread().getName();

        // 安全问题：
        // if (money >= balance) {
        //     System.out.println(name + "取款成功");
        //     balance -= money;
        //     System.out.println(name + "取款后，账户余额： " + balance);
        // } else {
        //     System.out.println("余额不足！");
        // }

        // 加锁方式一：synchronized 同步代码块
        //
        synchronized (this) {
            if (balance >= money) {
                System.out.println(name + "取款成功");
                this.balance -= money;
                System.out.println(name + "取款后，账户余额： " + balance);
            } else {
                System.out.println("余额不足！");
            }
        }
    }
}
