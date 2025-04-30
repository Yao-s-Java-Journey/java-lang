package com.thread.create;

import java.util.concurrent.Callable;

// 1. 定义任务类
public class MyCallable implements Callable<String> {
    private final int n;

    public MyCallable(int n) {
        this.n = n;
    }

    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i;
        }
        return "子线程求合结果：" + sum;
    }
}