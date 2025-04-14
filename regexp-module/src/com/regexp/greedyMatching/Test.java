package com.regexp.greedyMatching;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        // 在 + 后添加 ? 开启惰性模式
        Pattern pattern = Pattern.compile("欢迎(.+?)光临");
        Matcher matcher = pattern.matcher("欢迎张三光临" + "欢迎李四光临" + "欢迎王五光临");

        while (matcher.find()) {
            String user = matcher.group(1);
            System.out.println("用户：" + user);
        }
    }
}

