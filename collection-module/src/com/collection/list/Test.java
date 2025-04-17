package com.collection.list;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("张三");
        list.add("李四");
        list.add("赵六");

        // 插入
        list.add(2, "王五");

        // 索引删除
        String delEle = list.remove(2);
        System.out.println("被删除的数据：" + delEle);

        // 修改索引数据
        list.set(2, "孙五");

        // 取数据
        String data = list.get(2);
        System.out.println(data);
        System.out.println(list);
    }
}
