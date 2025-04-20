package com.mapDemo.hashMap;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("键", 123);
        map.put("键", 456); // 会覆盖
        map.put("张三", 1);
        map.put("李四", 2);
        map.put(null, null); // 键可以为 null

        System.out.println(map); // {null=null, 键=456}

    }

}
