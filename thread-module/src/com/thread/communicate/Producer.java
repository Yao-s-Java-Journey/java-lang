package com.thread.communicate;

// 生产者
public class Producer extends Thread {
    private final Platform platform;

    public Producer(String name, Platform platform) {
        super(name);
        this.platform = platform ;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                platform.putData();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
