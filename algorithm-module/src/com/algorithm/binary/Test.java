package com.algorithm.binary;

public class Test {
    public static void main(String[] args) {
        int[] arr = { 7, 23, 79, 81, 103, 127 };
        int index = binarySearch(arr, 170);
        System.out.println(index > 0 ? "查找的元素的对应索引：" + index : "未找到");
    }

    public static int binarySearch(int[] arr, int target) {
        // 1. 定义头尾指针
        int left = 0;
        int right = arr.length - 1;

        // 2. 开始折半查询
        while(left <= right) {
            // 3. 取中间索引
            int middle = (left + right) / 2;

            // 4. 判断当前要找的数据，与中间位置处的数据大小情况
            if (target > arr[middle]) {
                // 5. 往右边找，左边指针变更为 = 中间位置 + 1
                left = middle + 1;
            } else if (target < arr[middle]) {
                // 6. 往右边找，右边边指针变更为 = 中间位置 - 1
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
