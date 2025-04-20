package com.mapDemo.cities;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        // 省份级联案例

        // 省份
        Map<String, Set<String>> provinces = new HashMap<>();

        // 城市
        Set<String> cities1 = new HashSet<>();
        Collections.addAll(cities1, "南京", "苏州", "无锡", "南通");
        provinces.put("江苏省", cities1);

        Set<String> cities2 = new HashSet<>();
        Collections.addAll(cities2, "杭州", "宁波", "义乌", "温州");
        provinces.put("浙江省", cities2);

        System.out.println(provinces);

        // 取值
        Set<String> cities = provinces.get("浙江省");
        for (String city : cities) {
            System.out.println(city);
        }
    }
}
