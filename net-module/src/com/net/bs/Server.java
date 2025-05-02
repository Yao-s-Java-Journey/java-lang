package com.net.bs;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("服务端启动成功");

        while (true) {
            Socket client = server.accept();
            System.out.println(client.getRemoteSocketAddress() + " 来请求了");
            new ServerThread(client).start();
        }
    }
}
