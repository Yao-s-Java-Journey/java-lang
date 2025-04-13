package com.api.lambda;

import java.util.Arrays;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        System.out.println("简化函数式接口的匿名内部类代码：");
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
        System.out.println("\n-------------------\n");

        System.out.println("简化特定类型的方法引用：");
        String[] names = {"dlei", "Angela", "baby", "caocao", "coach", "曹操", "deby", "eason", "andy"};
        // 排序的正常写法：
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 忽略大小写，按首字母排序
                return o1.compareToIgnoreCase(o2);
            }
        });

        // 简化：
        Arrays.sort(names, (o1, o2) -> o1.compareToIgnoreCase(o2));
        // 终极简化：
        Arrays.sort(names, String::compareToIgnoreCase);

        System.out.println(Arrays.toString(names));
        System.out.println("\n-------------------\n");

        System.out.println("构造器的方法引用：");
        // 1. 正常写法：
        CreateCar creator = new CreateCar() {
            @Override
            public Car create(String name) {
                return new Car(name);
            }
        };
        // 2. 简写
        CreateCar creator1 = name -> new Car(name);
        // 3. 终极简写：
        CreateCar creator2 = Car::new;
        Car han = creator2.create("比亚迪 汉L");
        System.out.println(han.getName());
    }
}

@FunctionalInterface
interface Animal {
    //    有且仅有一个抽象方法
    void eat();
}

//interface CreateCar {
//    Car createCar();
//}