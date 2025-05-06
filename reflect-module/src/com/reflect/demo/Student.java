package com.reflect.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private String name;
    private int age;
    private double score;

    public Student() {
        System.out.println("Student 无参构造器");
    }

    private Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println(name + "喜欢吃饭");
    }
    public void eat(String food) {
        System.out.println(name + "喜欢零食" + food);
    }

    private void play(String sport) {
        System.out.println(name + "喜欢玩" + sport);
    }
}
