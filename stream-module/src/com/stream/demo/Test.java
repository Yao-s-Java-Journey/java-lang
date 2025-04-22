package com.stream.demo;

import java.util.*;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        // 1. 获取单链集合的 Stream 流
        Collection<String> list = new ArrayList<>();
        Collections.addAll(list, "张三", "李四", "王五");
        Stream<String> listStream = list.stream();
        System.out.println(listStream.count());

        // 2. 获取 Map 集合的 Stream 流
        Map<String, Integer> map = new HashMap<>();
        map.put("牛头", 19);
        map.put("马面", 20);
        // 2.1 获取键流
        Stream<String> keyStream = map.keySet().stream();
        // 2.2 获取值流
        Stream<Integer> valueStream = map.values().stream();
        // 2.1 获取键值对流
        Stream<Map.Entry<String, Integer>> entryStream = map.entrySet().stream();


        // 3. 获取数组的 Stream 流
        String[] names = {"王朝", "马汉", "张龙", "赵虎"};
        Stream<String> namesStream1 = Arrays.stream(names);
        Stream<String> namesStream2 = Stream.of(names);
    }
}
