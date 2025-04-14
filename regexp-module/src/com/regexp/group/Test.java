package com.regexp.group;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(\\w+)@(\\w+\\.\\w+)");
        Matcher matcher = pattern.matcher("My email is test@example.com");
        if (matcher.find()) {
            String localPart = matcher.group(1); // 获取本地部分（local part）
            String domain = matcher.group(2); // 获取域名部分（domain）
            System.out.println("Local Part: " + localPart);
            System.out.println("Domain: " + domain);
        } else {
            System.out.println("No match found.");
        }
    }
}