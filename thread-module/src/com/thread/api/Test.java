package com.thread.api;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new MyThread("1号线程");
        thread.start();
        System.out.println(thread.getName()); // Thread-0，当前线程的名称，默认 Thread-{index}

        // 主线程任务：
        Thread t = Thread.currentThread(); // 哪个线程在执行，就会得到哪个线程对象
        System.out.println(t.getName()); // 当前获取到主线程的名字 main
        for (int i = 0; i < 4; i++) {
            System.out.println("主线程i = " + i);

            // 满足特定条件时，让子线程插队执行
            if (i == 1) {
                thread.join();
            }
        }
    }
}

class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }
    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println(Thread.currentThread().getName()+ " 子线程的输出" + i);

            // 线程 sleep 延时
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}