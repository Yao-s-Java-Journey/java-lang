package com.collection.set;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        // 自定义排序方式一：类实现 Comparable 接口，重写 compareTo 方法
        // Set<Student> list = new TreeSet<>();

        // 自定义排序方式二：传递 Comparator 对象
//        Set<Student> list = new TreeSet<>(new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                return Double.compare(o2.getAge(), o1.getAge());
//            }
//        });
        Set<Student> list = new TreeSet<>((o1, o2) -> Double.compare(o2.getAge(), o1.getAge()));

        list.add(new Student("张三", 18));
        list.add(new Student("李四", 19));
        list.add(new Student("王五", 20));

        System.out.println("list = " + list);
    }
}
