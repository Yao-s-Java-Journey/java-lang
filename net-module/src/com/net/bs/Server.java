package com.net.bs;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("服务端启动成功");

        // 线程池优化
        ExecutorService pool = new ThreadPoolExecutor(
                8 * 2,
                8 * 2,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        while (true) {
            Socket client = server.accept();
            System.out.println(client.getRemoteSocketAddress() + " 来请求了");
            // 添加进线程池
            pool.execute(new ServerThread(client));
        }
    }
}
