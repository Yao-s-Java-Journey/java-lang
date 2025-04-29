package com.thread.create;

public class ThreadDemo {
    // main 方法本身是由一条主线程执行的
    public static void main(String[] args) {
        // 创建线程对象
        Thread thread = new MyThread();
        // 启动线程，自动执行 run() 方法
        thread.start();

        // 主线程的程序放在后面
        for (int i = 0; i < 4; i++) {
            System.out.println("主线程i = " + i);
        }
    }
}

class MyThread extends Thread {
    // 申明线程要干的事情
    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println("子线程i = " + i);
        }
    }
}