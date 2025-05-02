package com.net.wechat;

import lombok.AllArgsConstructor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

// 客户端接受消息
@AllArgsConstructor
public class ClientThread extends Thread {
    private Socket socket;

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            // 接受群聊消息中......
            while (true) {
                try {
                    String data = dis.readUTF();
                    System.out.println("群聊消息：" + data);
                } catch (IOException e) {
                    dis.close();
                    socket.close();
                    System.out.println("我已下线！");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
