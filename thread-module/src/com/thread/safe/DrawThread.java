package com.thread.safe;

// 从银行账户中取款的进程
public class DrawThread extends Thread {
    private final BankAccount account;

    public DrawThread(String name, BankAccount account) {
        super(name);
        this.account = account;
    }

    @Override
    public void run() {
        account.withdraw(100);
    }
}
