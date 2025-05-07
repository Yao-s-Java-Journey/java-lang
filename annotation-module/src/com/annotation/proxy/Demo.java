package com.annotation.proxy;

import org.junit.Test;

public class Demo {
    @Test
    public void run() {
        // 1. 创建明星
        RoleStar jayZhou = new RoleStar("周杰伦");
        // 2. 创建明星的经纪人
        Star broker = ProxyUtils.createBroker(jayZhou);

        String data = broker.sing("《晴天》"); // 会调用 invoke() 方法
        System.out.println(data);

        broker.dance();
    }
}
