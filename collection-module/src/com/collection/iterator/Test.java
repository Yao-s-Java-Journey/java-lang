package com.collection.iterator;

import java.util.ArrayList;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");

        // 获取集合的迭代器对象
        Iterator<String> iterator = list.iterator();

//        next() 方法返回迭代中的下一个元素
//        System.out.println(iterator.next()); // a
//        System.out.println(iterator.next()); // b
//        System.out.println(iterator.next()); // c
//        System.out.println(iterator.next()); // error NuSuchElementException

//        使用循环迭代
        while(iterator.hasNext()) {
            String ele = iterator.next();
            System.out.println(ele);
        }
    }
}
