package com.log.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
    // 1. 创建一个 Logback 框架的 Logger 日志对象
    public static final Logger LOGGER = LoggerFactory.getLogger("Test.class");

    public static void main(String[] args) {
        try {
            LOGGER.info("开始记录");
            division(1, 0);
            LOGGER.info("执行结束");
        } catch (Exception e) {
            LOGGER.error("错误日志：" + e.getMessage());
        }
    }

    public static void division(int a, int b) {
        LOGGER.debug("调试日志：" + a + "/" + b);
        int c = a / b;
        LOGGER.info("计算结果： " + c);
    }
}
