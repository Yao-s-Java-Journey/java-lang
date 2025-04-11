package com.oop.enums;

public class Test {
    public static void main(String[] args) {
        A x = A.X;
        A y = A.Y;
        A z = A.Z;
        System.out.println(x + " " + y + " " + z);

        System.out.println("------------------------");

//        values 方法用来获取枚举类的全部对象，返回一个数组
        A[] list = A.values();
        for (int i = 0; i < list.length; i++) {
            A item = list[i];
            System.out.println(item);
        }

//        valueOf 返回具有指定名称的指定枚举类型的枚举常量
        A z2 = A.valueOf("Z");
        System.out.println(z2 == z);

        // 3. ordinal 返回此枚举常量的序数索引
        System.out.println(x.ordinal()); // 0
        System.out.println(y.ordinal()); // 1
    }
}
