package com.api.lambda;

public class Test {
    public static void main(String[] args) {
        Animal cat = new Animal() {
            @Override
            public void eat() {
                System.out.println("小猫吃鱼");
            }
        };

        Animal dog = () -> {
            System.out.println("小狗吃肉");
        };
        dog.eat();
    }
}

@FunctionalInterface
interface Animal {
    //    有且仅有一个抽象方法
    void eat();
}