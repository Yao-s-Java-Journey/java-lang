package com.collection.set;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetDemo {
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<>();
        set.add("张无忌");
        set.add("张无忌");
        set.add("朱时茂");
        set.add("周芷若");
        set.add("周芷若");
        set.add("赵敏");

        // 有序，[张无忌, 朱时茂, 周芷若, 赵敏]
        System.out.println("set = " + set);
    }
}
