package com.thread.communicate;

// 消费生产模式
public class Test {
    public static void main(String[] args) {
        // 创建共享平台
        Platform platform = new Platform();

        // 创建两个消费者
        new Consumer("消费者1", platform).start();
        new Consumer("消费者2", platform).start();

        // 创建三个生产者
        new Producer("生产者1", platform).start();
        new Producer("生产者2", platform).start();
        new Producer("生产者3", platform).start();
    }
}
