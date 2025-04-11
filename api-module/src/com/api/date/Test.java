package com.api.date;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println("date = " + date);

        System.out.println(date.getYear()); // 年
        System.out.println(date.getMonthValue()); // 数字月
        System.out.println(date.getDayOfMonth()); // 当月第几天
        System.out.println(date.getDayOfYear()); // 当年第几天
        System.out.println(date.getDayOfWeek()); // 星期几（英文枚举）
        System.out.println(date.getDayOfWeek().getValue()); // 星期几（数字）
    }
}
