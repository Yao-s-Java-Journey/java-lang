package com.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) throws IOException {
        // 1. 创建服务端接收对象
        DatagramSocket socket = new DatagramSocket(8888);

        // 2. 创建数据包对象，作为接收数据的容器
        byte[] buf = new byte[1024 * 64];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        // 3. 接收数据
        socket.receive(packet);

        // 4. 获取数据
        int len = packet.getLength();
        String msg = new String(buf, 0, len);
        System.out.println("后端接收到的消息是：" + msg);

        // 获取发送端信息
        InetAddress ip = packet.getAddress();
        System.out.println("客户端ip：" + ip.getHostAddress());
        System.out.println("客户端端口：" + packet.getPort());

        // 5. 释放资源
        socket.close();
    }
}
