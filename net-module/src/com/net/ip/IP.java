package com.net.ip;

import java.net.InetAddress;

public class IP {
    public static void main(String[] args) throws Exception {
        InetAddress addr1 = InetAddress.getLocalHost();
        System.out.println("ip地址：" + addr1.getHostAddress());
        System.out.println("主机名：" + addr1.getHostName());

        // 获取对方 IP 地址对象
        InetAddress addr2 = InetAddress.getByName("www.baidu.com");
        System.out.println("ip地址：" + addr2.getHostAddress());
        System.out.println("主机名：" + addr2.getHostName());

        // 判断本机与主机是否联通（相当于 ping）
        System.out.println(addr2.isReachable(5000));
    }
}
