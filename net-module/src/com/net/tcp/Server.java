package com.net.tcp;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        // 1. 创建 ServerSocket 服务端对象
        ServerSocket server = new ServerSocket(8888);
        System.out.println("服务端启动成功");

        // 2. 主线程等待接受多个客户端的连接请求
        while (true) {
            // 3. 将客户端分发给独立的子线程进行处理
            Socket client = server.accept();
            System.out.println(client.getRemoteSocketAddress() + "上线了");
            new ServerThread(client).start();
        }
    }
}
