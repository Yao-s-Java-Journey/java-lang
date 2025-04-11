package com.api.stringJoiner;

import java.util.StringJoiner;

public class Test {
    public static void main(String[] args) {
        int[] list = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        String res = getArrayData(list);
        System.out.println(res);

    }

    public static String getArrayData(int[] arr) {
        if (arr == null) return null;

        StringJoiner str = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < arr.length; i++) {
            str.add(Integer.toString(arr[i]));
        }
        return str.toString();
    }

}
