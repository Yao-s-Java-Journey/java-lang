package com.thread.pool;

import com.thread.create.MyCallable;

import java.util.concurrent.*;

public class Create {
    public static void main(String[] args) {
        // 1.1 ThreadPoolExecutor 创建线程池
        ExecutorService pool = new ThreadPoolExecutor(
                3,
                5,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        // 1.2 Executors 创建线程池（大型并发系统中不建议使用，容易 OOM）
        // ExecutorService pool2 = Executors.newFixedThreadPool(3);

        // 2.1 处理任务
        Runnable target = new MyRunnable();
        pool.execute(target);
        pool.execute(target);
        pool.execute(target);
        pool.execute(target);

        // 2.2 处理 Callable 任务
        Future<String> f1 = pool.submit(new MyCallable(10));
        Future<String> f2 = pool.submit(new MyCallable(20));
        Future<String> f3 = pool.submit(new MyCallable(30));

        try {
            String res1 = f1.get();
            System.out.println("res1 = " + res1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String res2 = f2.get();
            System.out.println("res2 = " + res2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String res3 = f3.get();
            System.out.println("res3 = " + res3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3. 线程池没有死亡
        // pool.shutdownNow(); // 立即关闭，不管任务是否完成
        // pool.shutdown(); // 等待任务全部执行完毕再关闭线程
    }
}
