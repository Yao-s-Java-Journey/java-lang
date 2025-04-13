package com.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class Bubble {
    // 目标:完成冒泡排序的代码实现。
    public static void main(String[] args) {
        // 1、定义一个数组
        int[] arr = {5, 2, 3, 1};

        // 2、定义一个循环控制冒几轮.
        for (int i = 0; i < arr.length - 1; i++) {
            // 轮数(i)    每轮的次数   j 的占位
            // 第一轮0        3        0 1 2
            // 第二轮1        2        0 1
            // 第三轮2        1        0

            // 3、内部循环要控制每轮比较几次
            for (int j = 0; j < arr.length - i - 1; j++) {
                //14、判断当前位置j是否大于其后一个位置处的数据，若较大，则交换

                if (arr[j] > arr[j + 1]) {
                    //5、定义一个临时变量记住后一个位置处的数据
                    int temp = arr[j + 1];
                    //6、把前一个位置处的数据赋值给后一个位置处
                    arr[j + 1] = arr[j];
                    //7、把后一个位置原来的数据赋值给前一个位置处
                    arr[j] = temp;
                }
            }
        }

        System.out.println("arr = " + Arrays.toString(arr));
    }
}
