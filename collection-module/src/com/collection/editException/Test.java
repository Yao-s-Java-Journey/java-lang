package com.collection.editException;

import java.util.ArrayList;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        // 三种遍历可能出现的并发修改异常
        ArrayList<String> list = new ArrayList<>();
        list.add("Java入门");
        list.add("硫磺枸杞");
        list.add("黑枸杞");
        list.add("Java 进阶");
        list.add("宁夏枸杞");
        list.add("枸杞子");

        // 1. 迭代器的并发修改异常
//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            String ele = iterator.next();
//            if (ele.contains("枸杞")) {
//                // error，不能使用集合删除，出现并发修改错误
//                list.remove(ele);
//                // 必须调用迭代器自己的删除方法
//                iterator.remove();
//            }
//        }
//        System.out.println(list);

        // 2. 增强 for 的并发修改异常
//        for (String s : list) {
//            if (s.contains("枸杞")) {
//                // error，不能使用集合删除，出现并发修改错误
//                // 无法避免，for 只适合遍历，不能修改
//                list.remove(s);
//            }
//        }

        // 3. forEach 的并发修改异常
        list.forEach(s -> {
            if (s.contains("枸杞")) {
                // error，不能使用集合删除，出现并发修改错误
                // 无法避免
                list.remove(s);
            }
        });

        // 如果是 ArrayList 带索引的集合，也可以使用 for 循环删除并每次 i--
    }
}
