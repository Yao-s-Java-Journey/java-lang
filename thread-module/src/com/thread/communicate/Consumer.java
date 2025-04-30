package com.thread.communicate;

// 消费者
public class Consumer extends Thread {
    private final Platform platform;

    public Consumer(String name, Platform platform) {
        super(name);
        this.platform = platform;
    }

    // 消费生产者的物品
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                platform.useData();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
