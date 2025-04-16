package com.collection.forLoop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class Test {
    public static void main(String[] args) {
        Collection<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");

        //  1. for 遍历 Collection
        for (String s : list) {
            System.out.println(s);
        }

        //  2. for 遍历集合
        int[] arr = {1, 2, 3, 4};
        for (int i : arr) {
            System.out.println("i = " + i);
        }

        // 3. Lambda 表达式遍历
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        // 或
        list.forEach(s -> System.out.println(s));
        // 或
        list.forEach(System.out::println);
    }

}
