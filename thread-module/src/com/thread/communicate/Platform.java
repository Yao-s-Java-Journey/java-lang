package com.thread.communicate;

// 生产者和消费者共享的资源平台
public class Platform {
    // 生产者生产的数据
    private String data;

    public synchronized void useData() throws InterruptedException {
        String name = Thread.currentThread().getName();
        if (data == null) {
            // 没有数据，通知其他人
            this.notifyAll();
            this.wait();
        } else {
            System.out.println(name + "使用了" + data);
            data = null;
            this.notifyAll();
            this.wait();
        }
    }

    public synchronized void putData() throws InterruptedException {
        String name = Thread.currentThread().getName();
        if (data == null) {
            data = name + "生产的数据";
            this.notifyAll();
            this.wait();
        } else {
            this.notifyAll();
            this.wait();
        }
    }
}
