package com.net.tcp;

import lombok.AllArgsConstructor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 多线程多端通信
 * 一个线程处理一个客户端请求
 */
@AllArgsConstructor
public class ServerThread extends Thread {
    private Socket client; // 其中一个客户端

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            // 接受客户端数据中......
            while (true) {
                try {
                    String data = dis.readUTF();
                    System.out.println("服务端接受到的数据：" + data);
                } catch (IOException e) {
                    System.out.println(client.getRemoteSocketAddress() + "已下线！"); // 获取客户端的地址
                    dis.close();
                    client.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

