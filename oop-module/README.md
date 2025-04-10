# 内部类

类的五大成分：成员变量、方法、构造器、内部类、代码块。

如果一个类定义在另一个类的内部，这个类就是内部类。

## 形式1：成员内部类（了解）

1. 可以直接访问外部类的实例成员、静态成员；
2. 可以使用`外部类名.this` 获取当前外部类对象；

```java
public class People {
    private String name = "张三";

    // 成员内部类
    public class User {
        private String name = "李四";

        public void show() {
            String name = "王五";
            System.out.println(name); // 王五
            System.out.println(this.name); // 李四
            System.out.println(People.this.name); // 张三
        }
    }
}

// 创建成员内部类实例对象
People.User user = new People().new User();
```

## 形式2：静态内部类（了解）

1. 可以直接访问外部类的静态成员，不能访问外部类的实例成员

```java
public class People {
    private String name = "张三";
    private static double height;

    // 静态内部类
    public static class User {
        public void show() {
            // 可以访问外部类的静态成员
            System.out.println(height); // success

            // 不可以访问外部类的实例成员
            System.out.println(name); // error
        }
    }
}

// 创建静态内部类实例对象
People.User user = new People.User();
```

## 形式3：局部内部类（了解）

1. 定义在方法、代码块、构造器等执行体中；

```java
public class Test {
    public static void main(String[] args) {
        class A {
            // 鸡肋语法，没啥卵用
        }
    }
}
```

## 形式4：匿名内部类 ★★★

1. 特殊的局部内部类，匿名指不需要为这个类申明名字；
2. 本质是一个子类，并会立即创建出一个子类对象；
3. 用于更方便的创建一个子类对象
```java
public class Test {
    public static void main(String[] args) {
        // 创建匿名内部类对象
        // 匿名内部类的名称：“当前类名$编号”
        Animal dog = new Animal() {
            @Override
            public void eat() {
                System.out.println("小狗吃骨头");
            }
        };
        dog.eat();
    }
}

abstract class Animal {
    public abstract void eat();
}
```

### 使用场景
作为一个对象参数传输给方法使用。
```java
public class Test {
    public static void main(String[] args) {
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

interface Swimming {
    void swim();
}
```