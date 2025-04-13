package com.algorithm.sort;

import java.util.Arrays;

public class Selection {
    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1};

        for (int i = 0; i < arr.length - 1; i++) {
            // 轮数(i)    每轮的次数   j 的占位
            // 第一轮0        3        1 2 3
            // 第二轮1        2        2 3
            // 第三轮2        1        3

            // 定义一个变量，记住本轮最小值对应的索引
            int min = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }

        System.out.println("arr = " + Arrays.toString(arr));
    }
}
