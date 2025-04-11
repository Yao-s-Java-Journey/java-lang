package com.api.stringBuilder;

public class Test {
    public static void main(String[] args) {
        StringBuilder str = new StringBuilder();
        str.append("哈哈").append("呵呵").append("啊啊").append(123);
        System.out.println(str);

        System.out.println(str.reverse());
        System.out.println(str.toString());

        int [] list = {1,2,3,4,5,6,7,8,9};
        String res = getArrayData(list);
        System.out.println(res);

    }

    public static String getArrayData(int[] arr) {
        if (arr == null) return null;

        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < arr.length; i++) {
            str.append(arr[i]).append(i == arr.length - 1 ? "" : ",");
        }
        str.append("]");
        return str.toString();
    }
}
