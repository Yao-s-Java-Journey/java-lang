package com.stream.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "张三", "李四", "王二麻子");

        // 链式调用
        list.stream().filter(item -> item.length() < 3).forEach(System.out::println);
    }
}
