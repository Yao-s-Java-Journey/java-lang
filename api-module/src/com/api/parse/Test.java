package com.api.parse;

public class Test {
    public static void main(String[] args) {
        int a1 = 23;
        String a1Str = Integer.toString(a1); // "23"
        System.out.println(a1Str);

        Integer a2 = 23;
        String a2Str = a2.toString(a2); // "23"
        System.out.println(a2Str);

        String ageStr = "23";
        int age1 = Integer.parseInt(ageStr); // 23
        int age2 = Integer.valueOf(ageStr); // 23
        System.out.println(age1);
        System.out.println(age1 == age2);

        String ageDbl = "23.5";
        double age3 = Double.parseDouble(ageDbl); // 23.5
        double age4 = Double.valueOf(ageDbl); // 23.5
        System.out.println(age3 + 0.5); // 24.0
        System.out.println(age3 == age4);
    }
}
