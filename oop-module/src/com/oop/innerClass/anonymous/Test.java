package com.oop.innerClass.anonymous;

public class Test {
    public static void main(String[] args) {
//        1. 匿名内部类的使用方法
//        Animal dog = new Animal() {
//            @Override
//            public void eat() {
//                System.out.println("小狗吃骨头");
//            }
//        };
//        dog.eat();

//        2. 匿名内部类的使用场景
        Swimming s1 = new Swimming() {
            @Override
            public void swim() {
                System.out.println("蛙泳");
            }
        };
        go(s1);

        go(new Swimming() {
            @Override
            public void swim() {
                System.out.println("自由泳");
            }
        });
    }

    public static void go(Swimming s) {
        s.swim();
    }
}

abstract class Animal {
    public abstract void eat();
}

interface Swimming {
    void swim();
}