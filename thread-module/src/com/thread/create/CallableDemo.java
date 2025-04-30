package com.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableDemo {
    public static void main(String[] args) {
        // 2. 创建 Callable 对象
        Callable<String> call = new MyCallable(50);
        // 3. 封装成 FutureTask 对象
        //    FutureTask 是一个 Runnable 对象
        //    FutureTask 可以获取线程执行后的结果
        FutureTask<String> task = new FutureTask<>(call);
        // 4. 把 FutureTask 交给 Thread 线程对象
        Thread thread = new Thread(task);
        thread.start();

        // 5. 使用 FutureTask 的 get 方法获取结果
        try{
            String sum = task.get();
            System.out.println(sum);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}