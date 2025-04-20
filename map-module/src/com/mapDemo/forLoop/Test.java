package com.mapDemo.forLoop;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class Test {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("小米", 1);
        map.put("华为", 2);
        map.put("苹果", 3);
        map.put("诺基亚", 4);

        // 1. 遍历键，找值
        Set<String> keys = map.keySet();
        for (String key : keys) {
            int value = map.get(key);
            System.out.println(key + ":" + value);
        }

        // 2. entrySet + Map.Entry + for
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            String key = entry.getKey();
            int value = entry.getValue();
            System.out.println(key + ":" + value);
        }

        // 3. Lambda 遍历
        map.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String key, Integer value) {
                System.out.println(key + ":" + value);
            }
        });

        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
