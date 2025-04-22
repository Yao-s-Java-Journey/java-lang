package com.stream.demo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "张三", "李四", "王二麻子", "张三");

        // 收集到 list
        Stream<String> stream1 = list.stream();
        List<String> res1 = stream1.filter(item -> item.length() < 3).collect(Collectors.toList());
        // JDK 16 开始支持，并且只读不可修改
        // List<String> res2 = stream1.filter(item -> item.length() < 3).toList();
        System.out.println(res1);

        // 收集到 set
        Stream<String> stream2 = list.stream(); // stream 只能用一次，用完就要重新创建
        Set<String> res3 = stream2.filter(item -> item.length() < 3).collect(Collectors.toSet());
        System.out.println(res3);

        // 收集到数组
        Stream<String> stream3 = list.stream();
        Object[] res4 = stream3.filter(item -> item.length() < 3).toArray();
        System.out.println(Arrays.toString(res4));

        // 收集到 map
        List<Student> students = new ArrayList<>();
        students.add(new Student("张三", 18));
        students.add(new Student("张三", 20));
        students.add(new Student("李四", 22));
        students.add(new Student("王五", 24));

        // 取前三个，将名字作为健，年纪作为值
        Map<String, Integer> res5 = students.stream().limit(3).collect(Collectors.toMap(
                // 参数一：映射健；
                Student::getName,
                // 参数二：映射值；
                Student::getAge,
                // 参数三：二分合并操作，避免 Duplicate key 错误，当键名重复时，指定需要的值；
                (v1, v2) -> v1 + v2
        ));
        System.out.println(res5);
    }
}
