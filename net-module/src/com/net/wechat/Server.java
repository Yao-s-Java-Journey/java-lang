package com.net.wechat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    // 1. 收集客户端 Socket
    public static List<Socket> onLineSockets = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // 启动服务端
        ServerSocket server = new ServerSocket(8888);
        System.out.println("服务端启动成功");

        while (true) {
            // 2. 添加新加入的客户端
            Socket client = server.accept();
            onLineSockets.add(client);
            System.out.println(client.getRemoteSocketAddress() + "上线了");

            new ServerThread(client).start();
        }
    }
}
