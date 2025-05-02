package com.net.tcp;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        // 1. 创建 socket 对象，与服务端进行连接
        Socket client = new Socket(
                InetAddress.getLocalHost().getHostAddress(),
                8888
        );

        // 2. 从 socket 通信管道中获取字节输出流，用来发数据给服务端
        OutputStream os = client.getOutputStream();

        // 3. 把低级的字节输出流包装成数据输出流
        DataOutputStream dos = new DataOutputStream(os);

        // 4. 写数据出去（支持多发）
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入信息：");
            String msg = sc.nextLine();

            // 退出逻辑
            if (msg.equals("exit")) {
                dos.close();
                client.close();
                System.out.println("退出成功，欢迎下次使用");
                break;
            }

            dos.writeUTF(msg);
            dos.flush();
        }
    }
}
