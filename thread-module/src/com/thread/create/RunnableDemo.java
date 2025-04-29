package com.thread.create;

public class RunnableDemo {
    public static void main(String[] args) {
        // 创建 MyRunnable 任务对象，交由 Thread 处理
        Runnable target = new MyRunnable();
        Thread thread = new Thread(target);
        thread.start();

        // 简写
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                    System.out.println("子线程2的参数" + i);
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 4; i++) {
                System.out.println("子线程3的参数" + i);
            }
        });
        thread2.start();

        // 主线程的程序放在后面
        for (int i = 0; i < 4; i++) {
            System.out.println("主线程i = " + i);
        }
    }
}

class MyRunnable implements Runnable {
    // 申明线程要干的事情
    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println("子线程1的参数" + i);
        }
    }
}