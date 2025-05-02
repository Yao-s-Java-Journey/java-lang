package com.net.bs;

import lombok.AllArgsConstructor;

import java.io.PrintWriter;
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
            PrintWriter ps = new PrintWriter(client.getOutputStream(), true);
            ps.println("HTTP/1.1 200 OK");
            ps.println("Content-Type: text/html; charset=utf-8");
            ps.println(); // 换行
            ps.println("<html>后端响应数据</html>");
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

