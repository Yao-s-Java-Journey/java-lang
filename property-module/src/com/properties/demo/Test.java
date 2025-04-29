package com.properties.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws IOException {
        // 1. 创建容器
        Properties props = new Properties();
        // 2. 加载属性文件到容器中
        props.load(new FileInputStream("property-module\\src\\data.properties"));
        System.out.println("props = " + props);

        // 遍历
        props.forEach((key, value) -> {
            System.out.println(key + " = " + value);
        });

        // 取值
        System.out.println(props.get("name"));
        Set<String> keys = props.stringPropertyNames();
        for (String key : keys) {
            System.out.println(key + ": " + props.getProperty(key));
        }

        // 存值
        props.setProperty("gender", "male");
        props.store(
                new FileOutputStream("property-module\\src\\data.properties"),
                "新增属性"
        );
    }
}
