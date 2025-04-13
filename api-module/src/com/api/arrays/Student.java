package com.api.arrays;

public class Student implements Comparable<Student> {
    private String name;
    private int age;
    private char gender;
    private double height;

    public Student() {
    }

    public Student(String name, int age, char gender, double height) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", height=" + height +
                '}';
    }

    /**
     * 排序比较实现方式一：重写泛型接口的方法
     * @param o 被比较者
     * @return {int} 左边 > 右边：返回正整数，左边 < 右边：返回负整数，左边 = 右边：返回 0。
     */
    @Override
    public int compareTo(Student o) {
        return this.age - o.age; // 升序
//        return o.age - this.age; // 降序
    }


    public static int compareByHeight(Student o1, Student o2) {
        return Double.compare(o1.getHeight(), o2.getHeight());
    }
}
