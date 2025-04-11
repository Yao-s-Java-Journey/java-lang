package com.api.runtime;

public class Test {
    public static void main(String[] args) {
        Runtime jre = Runtime.getRuntime();

//        终止当前虚拟机，status 用作参数代码，非0状态码表示异常终止
//        jre.exit(0);

        System.out.println("处理器数量：" + jre.availableProcessors());
        System.out.println("Java 虚拟机中的内存总量：" + jre.totalMemory() / 1024 / 1024 + "MB");
        System.out.println("Java 虚拟机中的可用内存：" + jre.freeMemory() / 1024 / 1024 + "MB");

//        执行命令，启动程序
//        Process process = jre.exec()
    }
}