package com.regexp.compile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        // 1. 定义爬取规则对象，定义要爬取的格式
        Pattern pattern = Pattern.compile("[A-Z]");

        // 2. 通过匹配规则对象与内容建立联系，得到一个匹配器对象
        Matcher matcher = pattern.matcher("123Zahahaz" + "ZZZ");

        // 3. 使用匹配器对象爬取内容
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
