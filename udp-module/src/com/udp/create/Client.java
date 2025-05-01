package com.udp.create;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) throws IOException {
        // 1. 创建客户端发送对象
        DatagramSocket socket = new DatagramSocket();

        // 2. 创建数据包对象，封装要发送的数据
        byte[] buf = "客户端发送的数据".getBytes();
        DatagramPacket packet = new DatagramPacket(
                buf, // 数据
                buf.length, // 数据长度
                InetAddress.getLocalHost(), // 对方 IP
                8888 // 对方端口
        );

        // 3. 发送数据
        socket.send(packet);

        // 4. 释放资源
        socket.close();

        System.out.println("客户端发送完毕");
    }
}
