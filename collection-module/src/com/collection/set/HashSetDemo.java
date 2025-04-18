package com.collection.set;

import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("张无忌");
        set.add("张无忌");
        set.add("朱时茂");
        set.add("周芷若");
        set.add("周芷若");
        set.add("赵敏");

        // 无序，并没有按照 add 时的顺序排序
        // [周芷若, 赵敏, 张无忌, 朱时茂]
        System.out.println("set = " + set);
        System.out.println("\n-----------------\n");

        // HashSet 的去重机制
        Set<Student> list = new HashSet<>();
        Student s1 = new Student("张三", 18);
        Student s2 = new Student("李四", 19);
        Student s3 = new Student("李四", 19);

        list.add(s1);
        list.add(s2);
        list.add(s3);

        // 并没有去重 [{name=张三, age=18}, {name=李四, age=19}, {name=李四, age=19}]
        System.out.println(list);

        // 因为 s2 和 s3 的 hashcode 不一样，HashSet 先比较 hashCode，再比较内容
        System.out.println(s2.hashCode());
        System.out.println(s3.hashCode());

        // 解决：重写 Student 的 hashCode() 和 equals()
    }
}
