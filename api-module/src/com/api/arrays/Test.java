package com.api.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        Student[] list = new Student[4];
        list[0] = new Student("张三", 18, '男', 1.72);
        list[1] = new Student("李四", 19, '男', 1.82);
        list[2] = new Student("王五", 20, '男', 1.92);
        list[3] = new Student("赵六", 17, '男', 1.52);

//      排序方式一：让  Student  实现  Comparable  规则比较接口，重写  compareTo  方法，指定比较规则
//        Arrays.sort(list);


//      排序方式二：sort 存在重载方法，支持自带 Comparator 比较器对象来直接指定比较规则
        Arrays.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                // 按 int age 升序
//                 return o1.getAge() - o2.getAge();

                // 按 double height 升序
                return Double.compare(o1.getHeight(), o2.getHeight());
            }
        });

        System.out.println(Arrays.toString(list));
    }
}
