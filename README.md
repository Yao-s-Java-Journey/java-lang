# 一、static 关键字
## 特点
1. 静态成员属于类的成员，不属于实例对象的成员(非静态的成员属于对象成员)；
2. 静态成员会随着类的加载而加载；
3. 静态成员优先于非静态成员存在在内存中且在内存中只有一份；
4. 静态成员可以被类和类的所有实例对象共享。

```java
public class Student {
    // 静态变量
    static int total;
    // 实例变量
    String name;

    // 静态方法
    public static void fn1() {
        System.out.println("类和对象都能调用静态方法");
        System.out.println("只能访问静态成员（变量和方法）");
        System.out.println("不能使用 this");
    }

    public void fn2() {
        System.out.println("只有对象能调用");
        System.out.println("可以访问静态变量：" + total);
        System.out.println("可以调用静态方法");
        fn1();
    }
}
```

## 注意事项
1. 类方法中可以直接访问类的成员，不可以直接访问实例成员；
2. 实例方法中既可以直接访问类的成员，也可以直接访问实例成员；
3. 实例方法中可以出现 this 关键字（代表当前对象），类方法中不能出现 this 关键字。

# 二、代码块
## 静态代码块
```java
public class Test {
    public static ArrayList<String> users = new ArrayList();

    // 静态代码块
    static {
        System.out.println("类加载时执行");
        users.add("张三");
        users.add("李四");
    }

    public static void main(String[] args) {
        System.out.println(users); // ["张三", "李四"]
    }
}
```

1. 类加载时自动执行，由于类只会加载一次，所以静态代码块也只会执行一次；
2. 完成类的初始化，对类变量进行初始化赋值。



## 实例代码块
```java
public class Test {
    // 实例代码块
    {
        System.out.println("创建对象时先执行");
    }

    // 构造器
    public Test() {
        System.out.println("构造器后执行");
    }

    public static void main(String[] args) {
        new Test()
    }
}
```

1. 每次创建对象时，执行实例代码块，并在构造器之前执行； 
2. 和构造器一样，都用来完成对象的初始化。

# 三、static 与单例设计模式
确保一个类只有一个对象

## 写法
1. 把类的构造器私有（不允许被直接拿来实例化）；
2. 定义一个类变量记住类的一个对象；
3. 定义一个类方法，返回对象；

```java
public class SingleA {
    // 2. 静态变量保存实例对象
    private static SingleA a = new SingleA();
    
    // 1. 私有化构造器
    private SingleA() {}

    // 3. 封装一个静态方法暴露实例对象（防止被外部篡改）
    public static SingleA getSingleInstance() {
        return a;
    }
}
```

```java
public class SingleB {
    private static SingleB b;

    private SingleB() {}

    public static SingleB getSingleInstance() {
        // 在调用方法时才开始创建单例
        if (b == null) {
            b = new SingleB();
        }
        return b;
    }
}
```

# 四、继承
## 特点
1. 子类能继承父类的非私有成员（变量和方法）；
2. 子类创建的对象包含子类和父类的成员，但是能否访问取决于成员的访问权限。

```java
public class Father {
    public int i;
    private int j;

    public void printI() {
        System.out.println("i = " + i);
    }

    private void printJ() {
        System.out.println("j = " + j);
    }

    public int getJ() {
        return j 
    }
}
```

```java
public class Son extends Father {
    public int k;

    public void printK() {
        System.out.println("k = " + k);
    }
}
```

```java
public class Test {
    public static void main(String[] args) {
        Son s1 = new Son();
        System.out.println(s1.i); // success，父类实例变量
        System.out.println(s1.j); // error，私有变量无法访问
        System.out.println(s1.k); // success，子类实例变量

        s1.printI(); // success
        s1.printJ(); // error
        System.out.println("使用 get 获取父类私有变量：" + s1.getJ()); // success
    }
}
```

## 权限修饰符
| **权限修饰符** | **访问范围** |
| --- | --- |
| private | 只能本类 |
| 缺省 | 本类、同一个包中的类 |
| protected | 本类，同一个包中的类，子类 |
| public | 任意位置 |


## 单继承
1. Java 是单继承，一个类只能继承一个直接父类；
2. Java 不支持多继承，但支持多层继承；

```java
// Java 不允许一次多继承
class A extends B, C {}

// Java 支持多层继承
class D {}
class E extends D {}
class F extends E {}
```

3. Java 中所有的类都继承于`Object`

## 方法重写
### 特点
子类可以**重写一个方法名和参数列表一样，但是内部处理逻辑不同的方法**来覆盖父类中的同名方法，以满足自己的特定需求，这就是方法重写。

### 注意事项
1. 使用 `@Override`注解，它可以制定 Java 编译器，检查我们方法重写的格式是否正确，代码可读性也会更好；
2. 访问权限必须大于或等于父类方法的权限（public > protected > 缺省）；
3. 返回值类型必须与父类的方法返回值类型一致，或者范围更小；
4. 私有方法、静态方法不能重写，否则报错。

## super 访问父类成员
访问同名成员，默认依据就近原则，也可以使用关键字指定要访问的成员。

1. `this`访问自己的成员；
2. `super`访问父类的成员；

```java
class Father {
    String name = "父类名称";
}

class Son extends Father {
    String name= "子类名称";

    public void showName() {
        String name = "局部名称";

        System.out.println(name); // 就近原则 "局部名称"
        System.out.println(this.name); // "子类名称"
        System.out.println(super.name); // "父类名称"
    }
}
```

```java
class Father {
    @Override
    public void run() {
        System.out.println("子类方法");
    }

    public void go() {
        run(); // 执行子类的方法
        super.run(); // 执行父类的方法
    }
}

class Son extends Father {
    public void run() {
        System.out.println("父类方法");
    }
}
```

## 子类构造器
### 特点
子类的全部构造器（有参或无参）都会先调用父类的无参构造器，再执行自己。

```java
public class Animal {
    public Animal() {
        System.out.println("父类无参构造器");
    }

    public Animal(String name) {
        System.out.println("父类有参构造器");
    }
}

public class Tiger extends Animal {
    String name;
    public Tiger() {
        System.out.println("子类无参构造器");
    }

    public Tiger(String name) {
        System.out.println("子类有参构造器");
        this.name = name;
    }
}

// 都会先执行父类的无参构造器
Tiger tiger1 = new Tiger();
Tiger tiger2 = new Tiger("Tom");
```

### 注意事项
1. 默认情况下，子类全部构造器第一行代码都是 `super()`，它会调用父类的无参构造器；
2. 如果父类没有无参构造器（只写了有参构造器），则必须手动在子类构造器的第一行加上 `super(...)`，指定父类的有参构造器。

### 调用 super() 初始化
子类构造器通过 `super()`调用父类构造器，把对象中包含父类的部分数据先初始化赋值；

再把对象中包含子类的部分数据也初始化赋值。

```java
public class Animal {
    private String kind;

    public Animal() {
    }

    public Animal(String kind) {
        this.kind = kind;	
    }

    public String getKind() {
        return kind;
    }
}

public class Tiger extends Animal {
    private String skill;

    public Tiger() {
    }

    public Tiger(String kind, String skill) {
        // 调用 super 初始化父类的私有变量
        super(kind);
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}


public class Test {
    public static void main(String[] args) {
        Tiger tiger = new Tiger("老虎", "捕食");
        System.out.println(tiger.getSkill());
        System.out.println(tiger.getKind());
    }
}
```

### this() 调用兄弟构造器
1. 使用 `this()`，调用兄弟构造器；
2. `this()`和 `super()`不能同时出现，因为兄弟构造器内部也会执行 `super()`，会导致父类构造器执行两次；
3. `this()`和 `super()`必须在第一行；

```java
public class Animal {
    private String kind;
    private String habit;
    private String desc;

    public Animal() {
    }

    public Animal(String kind, String habit) {
        // 使用 this()，调用兄弟构造器
        this(kind, habit, "动物大世界");
    }
    
    public Animal(String kind, String habit, String desc) {
        this.kind = kind;
        this.desc = desc;
        this.habit = habit;
    }
}
```

# 五、多态
## 定义
1. 一定有**继承或实现**关系；
2. 存在父类引用子类对象；
3. 存在方法重写。

```java
public class Animal {
    String name = "动物";
    public void bark() {
        System.out.println("动物的叫声");
    }
}

// 1. Dog 继承自 Animal
public class Dog extends Animal {
    String name = "狗";

    // 2. 存在方法重写
    @Override
    public void bark() {
        System.out.println("汪汪汪");
    }

    public void guard() {	
        System.out.println("看门");
    }
}

// 3. 存在父类引用子类对象
Animal dog = new Dog(); // 编译看左边，执行看右边
dog.bark();
// 5. 不能调用子类独有的功能
dog.guard(); // error，不能调用 Dog 独有的功能，因为 Animal 没有 Dog 独有的 guard() 方法

// 4. Java 中的属性（成员变量）没有多态
System.out.println(dog.name); // 依旧是 "动物的叫声"
```

## 注意事项
1. 多态是对象和行为的多态，Java 中的属性（成员变量）没有多态；
2. 不能调用子类独有的功能；

## 类型转换
1. 只要有继承或实现关系的两个类就可以强转；
2. 编译时不会报错，但可能出现强制类型转换异常；

```java
Animal dog = new Dog();

dog.guard(); // error，Animal 没有 guard()

Dog dogCopy = (Dog) dog;
dogCopy.guard(); // success，Animal 强制转换成了 Dog

Cat catCopy = (Cat) dog; // success，没有报错，但其实把 dog 转成 cat 是不对的
catCopy.guard(); // 报错 cat 没有 guard()
```

## instanceof 与类型校验
建议类型收窄时，使用 `instanceof`进行校验

```java
if (dog instanceof Dog) {
    Dog dogCopy = (Dog) dog;
    dogCopy.guard();
}
```

# 六、final
可以修饰类、方法、变量

## 特点
1. 修饰类：被 `final`修饰的类成为`最终类`，不能再被继承了；
2. 修饰方法：被 `final`修饰的方法成为`最终方法`，不能被重写了；
3. 修饰变量：被 `final`修饰的变量只能被赋值一次。

```java
public class Test {
    public static final String USER_NAME = "张三";
    // 或者
    public static final int USER_AGE;
    static {
        USER_AGE = 18;
    }
}
```

# 七、抽象类
## 特点
1. 抽象类中不一定有抽象方法，有抽象方法的类一定是抽象类；
2. 类该有的成员（成员变量、方法、构造器）抽象类都可以有；
3. **抽象类不能创建对象**，仅作为一中特殊的父类，用来给子类继承并实现；
4. 一个类继承抽象类，必须重写完抽象类的全部抽象方法，否则这个类也必须定义成抽象类。

```java
public abstract class A {
    // 只有签名，没有具体实现
    public abstract void go()
}

public class B extends A {
    // 重写抽象类中定义的抽象方法
    @Override
    public void go() {
        System.out.println("重写抽象类的抽象方法");
    }
}

B b = new B();
b.go();
```

# 八、abstract 与模板方法设计模式
1. 解决方法中存在的重复代码的问题;
2. 建议使用 `final`保护模板方法，避免被重写导致失效；

```java
public abstract class People {
    public final void write() {
        System.out.println("《标题》");

        // 替换中间自定义的内容
        writeContent();

        System.out.println("结尾");
    }

    public abstract void writeContent();
}

public class Student extends People {
    @Override
    public void writeContent() {
        System.out.println("我爱上课");
    }
}

public class Teacher extends People {
    @Override
    public void writeContent() {
        System.out.println("我爱教课");
    }
}

People s = new Student()
s.write()

People t = new Teacher()
t.write()
```

# 九、接口
## 特点
1. 接口不能创建对象；
2. 接口用来被类实现`implements`，实现接口的类被称为`实现类`；
3. 接口与接口是多继承，一个接口可以同时继承多个接口；
4. 实现类实现多个接口，必须重写完全部接口定义的全部抽象方法，否则这个类必须是抽象类；

```java
public interface A {
    // 定义常量，可以省略 public static final
    String USER_NAME = "张三";

    // 定义抽象方法，可以省略 public abstract
    void run();
}

public interface B {
    void eat();
}

// 实现类
public class BImpl implements A, B {
    @Override
    public void run() {
        
    }

    @Override
    public void eat() {
        
    }
}

BI s1 = new BImpl();
A s2 = new BImpl();
B s3 = new BImpl();
```

```java
interface A {
    void a()
}

interface B {
    void b()
}

interface C extends A, B {
    void c()
}
```

## JDK 8+ 新增的三种接口方法
1. `default`修饰默认方法（普通方法）；
2. `private`修饰私有方法，可以通过接口内部的普通方法调用；
3. `static`修饰静态方法，只能用接口本身调用；

```java
public interface A {
    // 1. default 默认方法
    default void go() {
        run();
        System.out.println("普通的实例方法");
    }

    // 2. private 私有方法
    // 可以通过接口内部的普通方法调用
    private void run() {
        System.out.println("调用接口内部私有方法");
    }

    static void hello() {
        System.out.println("只能用接口本身调用");
    }
}

// 通过实现类调用普通方法
public class AImpl implements A {}


AImpl instance = new AImpl();
instance.go();

// 直接调用接口执行接口的静态方法
A.hello();
```

## 注意事项
1. 一个接口继承多个接口，如果多个接口中存在方法签名冲突（比如返回值类型不一样），则此时不支持多继承；
2. 一个类实现多个接口，如果多个接口中存在方法签名冲突，则此时不支持多实现；
3. 一个类继承了父类，又同时实现了接口，父类中和接口中有同名的默认方法，实现类会优先使用父类的；
4. 一个类实现了多个接口，多个接口中存在同名默认方法，可以不冲突，这个类重写该方法即可。

# 十、内部类
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
2. **本质是一个子类，并会立即创建出一个子类对象**；
3. 用于更方便的创建一个子类对象；

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
作为一个对象参数传输给方法使用

```java
public class Test {
    public static void main(String[] args) {
        // 直接实现 Swimming 接口，并立即创建一个实例对象
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

# 十一、枚举
## 特点
1. 枚举类的第一行只能罗列一些名称，这些名称都是常量，并且每个常量记住的都是枚举类的一个对象；
2. 枚举类的构造器是私有的，所以枚举类对外不能创建对象；
3. 枚举类是最终类，不可以被继承；
4. 枚举中，从第二行开始，可以定义类的其他各种成员；
5. 编译器为枚举类新增了几个方法，并且枚举类都是继承：java.lang.Enum 类的，从 enum 类也会继承到一些方法。

```java
public enum A {
    X, Y, Z

    // 也可以定义其他成员
    private int age;
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
}

public class Test {
    public static void main(String[] args) {
        A x = A.X;
        A y = A.Y;
        A z = A.Z;
        System.out.println(x + " " + y + " " + z);


        // 1. values 方法用来获取枚举类的全部对象，返回一个数组
        A[] list = A.values();
        for (int i = 0; i < list.length; i++) {
            A item = list[i];
            System.out.println(item);
        }

        // 2. valueOf 返回具有指定名称的指定枚举类型的枚举常量
        A z2 = A.valueOf("Z");
        System.out.println(z2 == z); // true

        // 3. ordinal 返回此枚举常量的序数索引
        System.out.println(x.ordinal()); // 0
        System.out.println(y.ordinal()); // 1
    }
}
```

## 使用场景
用作信息标志和信息分类

```java
public enum ActionType {
    DOWN, UP, HALF_UP, DELETE_LEFT;
}

public class Test {
    public static void main(String[] args) {
        handleData(3.9, ActionType.DOWN);
    }

    public static double handleData(double data, ActionType type) {
        switch(type) {
            case DOWN:
                data = Math.floor(data);
                break;
            case UP:
                data = Math.ceil(data);
                break;
            case HALF_UP:
                data = Math.round(data);
                break;
            case DELETE_LEFT:
                data = (int) data;
                break;
        }
        return data;
    }
}
```

# 十二、泛型
泛型变量建议用大写字母，比如，E、T、K、V 等。

## 泛型类
> 修饰符 class 类名<类型变量，类型变量，......>
>

## 泛型接口
> 修饰符 interface 接口名<类型变量，类型变量，......>
>

## 泛型方法、通配符、上下限
> 修饰符 <类型变量，类型变量，......> 返回值类型 方法名(形参列表) {}
>

```java
public static <T> T printArray(T[] a) {}
```

+ 通配符 `?` 表示任意类型；
+ 上下限 `? extends Car` 表示上限必须是 Car 或 Car 的子类；
+ 上下限 `? super Car` 表示下限必须是 Car 或 Car 的父类；

```java
public static void go(ArrayList<? extends Car> car) {}
```

## 泛型的擦除问题
1. 泛型工作在编译阶段，一旦程序编译成 class 文件，class 文件中就不存在泛型了，这就是泛型擦除；
2. 泛型不能直接支持基本数据类型，只能支持对象类型（引用数据类型）；

# 十三、Lambda 表达式
## 定义
只能简化**函数式接口**的**匿名内部类**代码。



> (被重写方法的形参列表) -> {
>
> 被重写的方法；
>
> }
>

## 函数式接口
+ 有且仅有一个抽象方法的接口；
+ 在大部分的函数式接口上，会有一个 `@FunctionalInterface`的注解，有该注解的接口必定是函数式接口。	

## 案例
```java
public class Test {
    public static void main(String[] args) {
        Animal cat = new Animal() {
            @Override
            public void eat() {
                System.out.println("小猫吃鱼");
            }
        };

        // 简化函数式接口
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
```

## 简化规则
1. 参数类型可以不写；
2. 如果只有一个参数，参数类型可以省略，同时()也可以省略；
3. 如果 Lambda 表达式中的方法体代码只有一行代码，可以省略大括号不写，同时要省略分号。此时，如果这行代码是 return 语句，也必须去掉 return 不写。

```java
// 简化前：
Arrays.sort(list, new Comparator<Student>() {
    @Override
    public int compare(Student o1, Student o2) {
        return Double.compare(o1.getHeight(), o2.getHeight());
    }
});

// 简化后：
Arrays.sort(list, (o1, o2) -> Double.compare(o1.getHeight(), o2.getHeight()));
```

# 十四、方法引用
## 静态方法的引用
如果某个 Lambda 表达式里只是调用一个静态方法，并且前后参数的形式一致（传递的参数列表和接受的参数列表），就可以使用静态方法引用。 

`类名::静态方法`

```java
public class Student {
    public static int compareByHeight(Student o1, Student o2) {
        return Double.compare(o1.getHeight(), o2.getHeight());
    }    
}

public class Test {
    public static void main(String[] args) {
        Student[] list = new Student[2];
        list[0] = new Student("张三", 18, '男', 1.72);
        list[1] = new Student("李四", 19, '男', 1.82);

        // 调用静态方法
        Arrays.sort(list, (o1, o2) -> Student.compareByHeight(o1, o2));
        
        // 简写：
        // 1. 调用的是 compareByHeight 静态方法；
        // 2. 前面传递的参数列表和后面接受的参数列表一致；
        Arrays.sort(list, Student::compareByHeight);
    }
}
```

## 实例方法的引用
与静态方法的引用类似

```java
public class Test {
    public static void main(String[] args) {
        Test t = new Test();
        Arrays.sort(list, t::compareByHeight);
    }

    public int compareByHeight(Student o1, Student o2) {
        return Double.compare(o1.getHeight(), o2.getHeight());
    }
}
```

## 特定类型方法的引用
如果某个Lambda表达式里只是调用一个实例方法，并且前面参数列表中的第一个参数是作为方法的主调，后面的所有参数都是作为该实例方法的入参的，则此时就可以使用特定类型的方法引用。

```java
public class Test {
    public static void main(String[] args) {
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
        // 1. 调用的是 o1 实例的方法
        // 2. o1 是函数体方法的主调
        // 3. o2 是实例方法的入参
        Arrays.sort(names, (o1, o2) -> o1.compareToIgnoreCase(o2));

        // 终极简化：
        Arrays.sort(names, String::compareToIgnoreCase);
    }
}
```

## 构造器的引用
如果 Lambda 表达式里只是在创建对象，并且前后参数情况一致，就可以使用构造器引用。

```java
// Car 实体类
public class Car {
    private String name;
    public Car() {}
    public Car(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

// 创造 Car 的接口
@FunctionalInterface
interface CreateCar {
    Car create(String name);
}

public Test {
    public static void main(String[] args) {
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
```

# 十五、正则表达式
## 书写规则
| **字符类（只匹配单个字符）** | |
| --- | --- |
| [abc] | 只能是a，b，或c |
| [^abc] | 除了a，b，c之外的任何字符 |
| [a-zA-Z] | a到z A到z，包括(范围) |
| [a-d[m-p]] | a到d，或m到p |
| [a-z&&[def]]  | d，e，或f(交集) |
| [a-z&&[^bc]] | a到z，除了b和c(等同于[ad-z]) |
| [a-z&&[^m-p]]: | a到z，除了m到p(等同于[a-1q-z]) |
| **预定义字符(只匹配单个字符)** | |
| . | 任何字符 |
| \d | 一个数字: [0-9] |
| \D | 非数字:[^0-9] |
| \s | 一个空白字符 |
| \S | 非空白字符: [^\s] |
| \w | [a-zA-Z 0-9] |
| \W | [^\w]一个非单词字符 |
| **数量词** | |
| x? | X，一次或0次 |
| x* | X，零次或多次 |
| x+ | X，一次或多次 |
| x {n} | X，正好n次 |
| x {n, } | X，至少n次 |
| x {n, m} | X，至少n但不超过m次 |


## [Pattern.compile](https://www.runoob.com/manual/jdk11api/java.base/java/util/regex/Pattern.html#compile(java.lang.String))
```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        // 1. 定义爬取规则对象，定义要爬取的格式
        Pattern pattern = Pattern.compile("[A-Z]");

        // 2. 通过匹配规则对象与内容建立联系，得到一个匹配器对象
        Matcher matcher = pattern.matcher("123Zahahaz" + "ZZZ");

        // 3. 使用匹配器对象爬取内容
        while (matcher.find()) {
            String res1 = matcher.group();
            String res2 = matcher.group(1); // 只要爬取出来的邮箱中的第一组括号内容
            System.out.println(res1);
            System.out.println(res2);
        }
    }
}
```

## 分组爬取
使用小括号`()`，创建分组。

```java
import java.util.regex.Pattern;
import java.util.regex.Matcher;
 
public class RegexExample {
    public static void main(String[] args) {
        // 创建Pattern对象，使用 () 创建分组
        Pattern pattern = Pattern.compile("(\\w+)@(\\w+\\.\\w+)");

        // 创建Matcher对象
        String input = "My email is test@example.com";
        Matcher matcher = pattern.matcher(input);

        // 查找匹配项并获取分组内容
        if (matcher.find()) {
            String localPart = matcher.group(1); // 获取本地部分（local part）
            String domain = matcher.group(2); // 获取域名部分（domain）
            System.out.println("Local Part: " + localPart);
            System.out.println("Domain: " + domain);
        } else {
            System.out.println("No match found.");
        }
    }
}
```

## 贪婪与惰性
**<font style="color:rgb(27, 27, 27);">紧跟在任何量词 *、 +、? 或 {} 的后面</font>**<font style="color:rgb(27, 27, 27);">，将会使量词变为</font>**<font style="color:rgb(27, 27, 27);">非贪婪</font>**<font style="color:rgb(27, 27, 27);">（匹配尽量少的字符），和缺省使用的</font>**<font style="color:rgb(27, 27, 27);">贪婪模式</font>**<font style="color:rgb(27, 27, 27);">（匹配尽可能多的字符）正好相反。</font>

```java
public class RegexExample {
    public static void main(String[] args) {
        // 在 + 后添加 ? 开启惰性模式
        Pattern pattern = Pattern.compile("欢迎(.+?)光临");

        String input = "欢迎张三光临" + "欢迎李四光临" + "欢迎王五光临";
        Matcher matcher = pattern.matcher(input);

        // 查找匹配项并获取分组内容
        while (matcher.find()) {
            String user = matcher.group(1);
            System.out.println("用户：" + user);
        }
    }
}
```

## 搜索替换与内容分割
结合 String 提供的以下方法实现

| 方法名 | 说明 |
| --- | --- |
| public String replaceAll(String regex, String newStr) | 按照正则表达式匹配的内容进行替换 |
| public String[] split(String regex) | 按照正则表达式匹配的内容进行分割字符串，返回一个字符串数组 |


```java
public class Test {
    public static void main(String[] args) {
        String str = "古力娜扎asd12迪丽热巴gdf241马尔扎哈88986ui卡尔扎巴";

        // 替换
        String res = str.replaceAll("\\w+", "-");
        System.out.println(res);
        System.out.println("\n-----------------\n");

        // 分割
        String[] names = str.split("\\w+");
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
    }
}
```

# 十六、异常与捕获
## 常见异常
| 示例 | 说明 | 异常 |
| --- | --- | --- |
| int[] arr = {1};<br/>arr[2]; | 数组索引越界异常 | ArrayIndexOutofBoundsException |
| String name = null;<br/>name.length; | 空指针异常 | NullPointerException |
| 10 / 0; | 数学操作异常 | ArithmeticException |
| Object o = "哈哈";<br/>Integer i = (Integer) o; | 类型转换异常 | ClassCastException |
| String s = "23a";<br/>int i = Integer.valueOf(s); | 数字转换异常 | NumberFormatException |


**Error**：代表系统级别错误；

**Exception**：异常，代表程序出现问题，通常会用 Exception 以及子类来封装程序出现的问题。

+ **运行时异常**：RuntimeException 及其子类，编译阶段不会出现错误提醒，运行时出现的异常（比如数组索引越界异常）；
+ **编译时异常**：编译阶段就会出现错误提醒。（比如日期解析异常）；

## 捕获异常
1. `throw new Exception("xxxx")`来抛出异常；
2. `try...catch() {...}`来捕获异常；
3. `finally`无论正常还是异常，最后一定会执行 finally 代码块中的程序（即便加个 `return`也会执行，除非 JVM 终止）
4. 不要在 `finally`中 return 数据，不然会覆盖之前 return 的数据。

```java
public class Test {
    public static void main(String[] args) {
        try {
            int res = divide(10, 0);
            System.out.println("计算结果: " + res);
        } catch (RuntimeException e) {
            // 2. catch 捕获异常并打印异常信息
            System.out.println("捕获到异常");
            e.printStackTrace();
        } finally {
            System.out.println("程序运行结束");   
        }
    }

    public static int divide(int a, int b) {
        if (b == 0) {
            // 1. 抛出数学计算异常
            throw new ArithmeticException("Divide by zero");
        } else {
            return a / b;
        }
    }
}
```

## 自定义异常
### 运行时异常
1. 定义一个异常类继承 `RuntimeException`;
2. 重写构造器；
3. 通过 `throw new 异常类(xxx)`来创建异常对象并抛出。

```java
// 自定义运行时异常
public class CustomRuntimeException extends RuntimeException {
    public CustomRuntimeException() {}
    public CustomRuntimeException(String message) {
        super(message);
    }

}

public class Test {
    public static void main(String[] args) {
        try {
            setAge(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setAge(int age) {
        if (age < 1 || age > 100) {
            throw new CustomRuntimeException("非法年龄");
        }
        System.out.println("年龄合法");
    }
}
```

### 编译时异常
1. 定义一个异常类继承 `Exception`;
2. 重写构造器；
3. 通过 `throw new 异常类(xxx)`来创建异常对象并抛出；
4. 在方法签名上添加`throws 异常类`，抛出异常（否则代码会报错）；

```java
// 自定义运行时异常
public class CustomRuntimeException extends RuntimeException {
    public CustomRuntimeException() {}
    public CustomRuntimeException(String message) {
        super(message);
    }
}

public class Test {
    public static void main(String[] args) {
        try {
            setName(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 方法签名添加 throws CustomCompileException 抛出错误
    public static void setName(String name) throws CustomCompileException {
        if (name == null) {
            throw new CustomCompileException("非法姓名");
        }
        System.out.println("名字合法");
    }
}
```

建议使用运行时异常，避免频繁和非必要的报错。

## 异常的处理
方式一：捕获异常，记录并响应合适信息给用户；

方式二：捕获异常，尝试重新修复；

# 十七、集合进阶
## 集合的体系
![画板](https://cdn.nlark.com/yuque/0/2025/jpeg/29092218/1744790787102-b7adbd3c-ad12-4b0d-a256-7077730fe3d9.jpeg)

## Collection
Collection 是单例集合的祖宗，全部单例集合都会继承它规定的方法（功能）。

| 方法名 | 说明 |
| --- | --- |
| public boolean add(E e) | 把给定的对象添加到当前集合中 |
| public void clear() | 清空集合中所有的元素 |
| public boolean remove(E e) | 把给定的对象在当前集合中删除 |
| public boolean contains(Object obj) | 判断当前集合中是否包含给定的对象 |
| public boolean isEmpty() | 判断当前集合是否为空 |
| public int size() | 返回集合中元素的个数 |
| public Object[] toArray() | 把集合中的元素，存储到数组中 |
| boolean addAll(Collection<? extends E> c) | <font style="color:rgb(71, 71, 71);">将指定集合中的所有元素添加到此集合中</font> |


```java
Collection<string> list1 = new ArrayList<>();
list1.add("ab");
list1.add("cd");
list1.add("ef");

// 将字符串集合转成字符串数组
String[] arrays = list1.toArray(String[]::new)

// 把 list2 的数据添加进 list1
Collection<string> list2 = new ArrayList<>();
list1.addAll(list2);
```

## Collection 的遍历方式
### 迭代器遍历
迭代器是用来遍历集合的专用方式（数组没有迭代器）

```java
public class Test {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");

        // 获取集合的迭代器对象
        Iterator<String> iterator = list.iterator();

//        next() 方法返回迭代中的下一个元素
//        System.out.println(iterator.next()); // a
//        System.out.println(iterator.next()); // b
//        System.out.println(iterator.next()); // c
//        System.out.println(iterator.next()); // 溢出 error NuSuchElementException

//        使用循环迭代
        while(iterator.hasNext()) {
            String ele = iterator.next();
            System.out.println(ele);
        }
    }
}
```

### 增强 for 遍历
用来遍历集合或者数组

`for (类型 变量 : 集合)`

```java
// 遍历 Collection
Collection<String> list = new ArrayList<String>();
list.add("a");
list.add("b");
for (String s : list) {
    System.out.println(s);
}

// 遍历数组
int[] arr = {1, 2, 3, 4};
for (int i : arr) {
    System.out.println("i = " + i);
}
```

### forEach 遍历
使用 Collection 的 `forEach`方法来完成

```java
default void forEach(Consumer<? super T> action) 
```

```java
Collection<String> list = new ArrayList<String>();
list.add("a");
list.add("b");

list.forEach(new Consumer<String>() {
    @Override
    public void accept(String s) {
        System.out.println(s);
    }
});

// 简写
list.forEach(s -> System.out.println(s));

// 方法引用简写
list.forEach(System.out::println);
```

## 集合的并发修改异常
使用迭代器遍历集合时，又同时在删除集合中的数据，程序就会出现并发修改异常的错误。

**注意：**

1. **Iterator 遍历、增强 for 遍历、forEach 遍历中使用集合自带的 **`**remove()**`**方法都会出现并发修改异常错误；**
2. **如果需要在遍历时删除数据，请使用 Iterator 自带的 **`**remove**`**方法进行删除。**

```java
public class Test {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Java入门");
        list.add("硫磺枸杞");
        list.add("黑枸杞");
        list.add("Java 进阶");

        // 1. 迭代器的并发修改异常
       Iterator<String> iterator = list.iterator();
       while (iterator.hasNext()) {
           String ele = iterator.next();
           if (ele.contains("枸杞")) {
               list.remove(ele); // error
           }
       }

        // 2. 增强 for 的并发修改异常
       for (String s : list) {
           if (s.contains("枸杞")) {
               list.remove(s); // error
           }
       }

        // 3. forEach 的并发修改异常
        list.forEach(s -> {
            if (s.contains("枸杞")) {
                list.remove(s); // error
            }
        });
    }
}
```

```java
Iterator<String> iterator = list.iterator();
while (iterator.hasNext()) {
   String ele = iterator.next();
   if (ele.contains("枸杞")) {
       // 只能使用 Iterator 自带的 remove() 进行删除
       iterator.remove();
   }
}
```

如果非要用 for 循环，可以倒叙删除，或者使用 i-- 的方式进行删除。

## List 集合
### ArrayList 和 LinkedList 的区别
底层采用的数据结构不用，应用场景不同

### ArrayList 集合
#### 特点
+ 基于数组实现；
+ 查询速度快

特指根据索引寻址速度快，查询数据通过地址值和索引定位，查询任意数据耗时相同。

+ 删除效率低

可能需要把后面很多的数据进行迁移

+ 添加效率低

可鞥需要把后面很多数据后移，再添加元素，或者也可能需要进行数组的扩容。

#### 底层原理
1. 利用无参构造器创建的集合，会在底层创建一个默认长度为 0 的数组；
2. 添加第一个元素时，底层会创建一个新的长度为 10 的数组；
3. 存满时，会扩容 1.5 倍；
4. 如果一次添加多个元素，1.5 倍还放不下，则新创建数组的长度以实际长度为准；

```java
// 开发中建议使用多态创建 ArrayList 集合
List<String> list = new ArrayList<String>();
```

### LinkedList 集合
#### 特点
+ 基于双链表实现；
+ 查询慢，增删相对较快，尤其是首尾节点；

#### 链表首尾操作的特有方法
| 方法名 | 说明 |
| --- | --- |
| public void addFirst(E e) | 在该列表开头插入指定的元素 |
| public void addLast(Ee) | 将指定的元素追加到此列表的末尾 |
| public E getFirst() | 返回此列表中的第一个元素 |
| public E getLast() | 返回此列表中的最后一个元素 |
| public E removeFirst() | 从此列表中删除并返回第一个元素 |
| public E removeLast() | 从此列表中删除并返回最后一个元素 |


```java
LinkedList<String> queue1 = new LinkedList<String>();
// 按队列添加元素（先进）
queue1.addLast("第一个人");
queue1.addLast("第二个人");
queue1.addLast("第三个人");

// 按队列移除元素（先出）
queue1.removeFirst();
queue1.removeFirst();
System.out.println("queue = " + queue1);
```

```java
LinkedList<String> queue2 = new LinkedList<String>();
// 想象一个右端封口，左端开口的栈： 
// 按栈添加元素（进栈）
queue2.push("第一颗子弹"); // 就是 addFirst()
queue2.push("第二颗子弹");
queue2.push("第三颗子弹");
System.out.println("queue2 = " + queue2); // [第三颗子弹, 第二颗子弹, 第一颗子弹]

// 按栈移除元素（出栈）
queue2.pop(); // 就是 removeFirst()
System.out.println("queue2 = " + queue2); // [第二颗子弹, 第一颗子弹]
```

## Set 集合
Set 系列集合特点：

1. 无序，添加数据的顺序和获取出的数据顺序不一致；
2. 不重复；
3. 无索引

Set 要用到的常用方法，基本上都是 Collection 提供的，自己几乎没有额外新增一些功能。

### 不同 Set 之间的区别
+ HashSet：无序、不重复、无索引；
+ LinkedHashSet：有序、不重复、无索引；
+ TreeSet：默认按元素大小升序排序、不重复、无索引；

### Hash 值
#### 定义
+ 是一个 int 类型的随机数值，Java 中每一个对象都有一个哈希值；
+ Java 中所有的对象，都可以调用 Object 类提供的 `hashCode()`方法，返回该对象自己的哈希值；

#### 对象哈希值的特点
+ 同一个对象多次调用 `hashCode()`方法返回的哈希值是相同的；
+ 不同对象，它们的哈希值一般不同，但也有可能会相同（哈希碰撞）；

### HashSet
```java
Set<String> set = new HashSet<>();
```

#### 底层原理
+ 基于哈希表实现

#### HashSet 的去重机制
1. 先看元素的哈希值是否一样；
2. 再看元素的内容是否一样。

所以，HashSet 集合默认不能对内容一样的两个不同对象去重。

解决：必须重写对象的 `hasCode()`和 `equals()`方法。

```java
public class Student {
    private String name;
    private int age;
    public Student() {}
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "{name=" + name + ", age=" + age + "}";
    }

    // 只要两个对象的内容一样，返回的哈希值就是一样的
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    // 比较内容
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }
}


public class HashSetDemo {
    public static void main(String[] args) {
        Set<Student> list = new HashSet<>();
        Student s1 = new Student("张三", 18);
        Student s2 = new Student("李四", 19);
        Student s3 = new Student("李四", 19);
        list.add(s1);
        list.add(s2);
        list.add(s3);

        // 并没有去重 [{name=张三, age=18}, {name=李四, age=19}, {name=李四, age=19}]
        System.out.println(list);

        // 因为 s2 和 s3 的 hashcode 不一样，HashSet 先比较 hashCode，再比较内容
        System.out.println(s2.hashCode());
        System.out.println(s3.hashCode());

        // 解决：重写 Student 的 hashCode() 和 equals()
    }
}
```

### LinkedHashSet
#### 底层原理
+ 基于哈希表实现；
+ 每个元素都额外的多了一个双链表机制记录它前后元素的位置；

### TreeSet
#### 底层原理
+ 基于红黑树实现的排序
+ 对于数值类型，Integer、Double，默认按照数值本身的大小进行升序排序；
+ 对于字符串类型，默认按照首字符的编号生序排序；

#### 自定义排序规则
+ TreeSet 集合存储自定义类型的对象时，必须指定排序规则，支持如下两种方式来指定：
+ 方式一：让自定义的类实现 `Comparable`接口，重写 `compareTo()`方法来指定规则；
+ 方式二：通过调用 TreeSet 集合有参构造器，可以设置 `Comparator`对象（比较器对象，用于指定比较规则）`public TreeSet (Comparator<? super E> comparator)`

```java
public class Student implements Comparable<Student> {
    private String name;
    private int age;

    public Student() {}
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "{name=" + name + ", age=" + age + "}";
    }

    @Override
    public int compareTo(Student o) {
        // return this.age - o.age; // 升序
         return o.age - this.age; // 降序
    }
}

public class TreeSetDemo {
    public static void main(String[] args) {
        // 自定义排序方式一：类实现 Comparable 接口，重写 compareTo 方法
        // Set<Student> list = new TreeSet<>();

        // 自定义排序方式二：传递 Comparator 对象
//        Set<Student> list = new TreeSet<>(new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                return Double.compare(o2.getAge(), o1.getAge());
//            }
//        });
        Set<Student> list = new TreeSet<>((o1, o2) -> Double.compare(o2.getAge(), o1.getAge()));

        list.add(new Student("张三", 18));
        list.add(new Student("李四", 19));
        list.add(new Student("王五", 20));

        System.out.println("list = " + list);
    }
}
```

## 使用场景总结
| Set 集合 | 使用场景 |
| --- | --- |
| ArrayList ★★★ | 记住元素的添加顺序，需要存储重复的元素，又要频繁的根据索引查询数据 |
| LinkedList | 希望记住元素的添加顺序，且增删首尾数据的情况较多 |
| HashSet ★★★ | 不在意元素顺序，也没有重复元素需要存储，只希望增删改查都快 |
| LinkedHashSet | 记住元素的添加顺序，也没有重复元素需要存储，且希望增删改查都快 |
| TreeSet | 对元素进行排序，也没有重复元素需要存储且希望增删改查都快? |


# 十八、Map
+ Map 集合被称为双链集合（键值对集合），一次需要存一对数据作为一个元素；
+ Map 集合的所有键不允许重复；

## Map 体系
![画板](https://cdn.nlark.com/yuque/0/2025/jpeg/29092218/1745826842075-0e9cd401-89e5-4b11-8baf-47e1eeed18e3.jpeg)

+ **HashMap**(由键决定特点)：无序、不重复、无索引，(用的最多)；
+ **LinkedHashMap** (由键决定特点)：有序、不重复、无索引；
+ **TreeMap** (由键决定特点)：按照大小默认升序排序、不重复、无索引。

## Map 常用方法
Map 是双链集合的祖宗，所有的双链集合都可以继承使它的功能。

| 方法 | 说明 |
| --- | --- |
| public V put(K key,V value) | 添加元素 |
| public int size() | 获取集合的大小 |
| public void clear() | 清空集合 |
| public boolean isEmpty() | 判断集合是否为空，为空返回 true，反之 |
| public v get(object key) | 根据键获取对应值，没有返回 null |
| public V remove(Object key) | 根据键删除整个元素 |
| public boolean containsKey(Object key) | 判断是否包含某个键 |
| public boolean containsValue(Object value) | 判断是否包含某个值 |
| public Set\<K> keySet() | 获取全部键的集合 |
| public Collection\<V>values() | 获取 Map 集合的全部值 |


## Map 的遍历方式
### 键找值
先获取 Map 集合全部的键，再通过遍历键来找值。

```java
Map<String, Integer> map = new HashMap<>();
map.put("小米", 1);
map.put("华为", 2);
map.put("苹果", 3);

Set<String> keys = map.keySet();
for (String key : keys) {
    int value = map.get(key);
    System.out.println(key + ":" + value);
}
```

### 键值对
+ 使用 `entrySet()`获取整个 Map 的 Set 视图；
+ 使用 `Map.Entry<键类型, 值类型>`为 Map 中的元素创建一个“键值对类型”；
+ 使用增强 for 遍历整个视图。

```java
Set<Map.Entry<String, Integer>> entries = map.entrySet();
for (Map.Entry<String, Integer> entry : entries) {
    String key = entry.getKey();
    int value = entry.getValue();
    System.out.println(key + ":" + value);
}
```

### forEach + Lambda 表达式
| default void forEach(BiConsumer<? supre K, ? super V> action) | 结合 Lambda 遍历 Map 集合 |
| --- | --- |


```java
map.forEach(new BiConsumer<String, Integer>() {
    @Override
    public void accept(String key, Integer value) {
        System.out.println(key + ":" + value);
    }
});

map.forEach((k, v) -> System.out.println(k + ":" + v));
```

## HashMap
+ HashMap 和 HashSet 的底层原理是一样的，都是基于哈希表实现的；
+ Set 系列集合的底层就是基于 Map 实现的，只是 Set 集合中的数据只要键数据，不要值数据而已；
+ 依赖 `hashCode()`和 `equals()`方法保证了键的唯一。

```java
Map<String, Integer> map = new HashMap<>();
```

## LinkedHashMap
+ 有序、不重复、无索引；
+ 底层数据结构依然是基于哈希表实现的，只是每个键值对又额外多了一个双链表的机制记录元素的顺序（保证有序）；

## TreeMap
+ 按照键的大小默认升序排序、不重复、无索引；
+ 和 TreeSet 的底层原理是一样的，都是基于红黑树实现的排序；
+ 同样支持两种方式指定排序规则：
    - 让类实现 `Comparable`接口，重写比较规则；
    - TreeMap 集合有一个有参构造器，支持创建 `Comparator` 比较器对象，以便用来指定比较规则。

# 十九、Stream 流
JDK8 新增的一套 API，可以用于操作集合或者数组的数据。大量结合了 Lambda 的语法风格来编程，代码更简洁，可读性更好。

## 使用步骤
1. 获取 Stream 流；
2. 调用流水线方法；
3. 获取处理结果，收集到新集合中返回。

## 获取 Stream 流
+ 获取集合的 Stream 流

| Collection 提供的方法 | 说明 |
| --- | --- |
| default Stream\<E> stream() | 获取当前集合对象的 Stream 流 |


+ 获取数组的 Stream 流（2种）

| Arrays 提供的方法 | 说明 |
| --- | --- |
| public static \<T> Stream\<T> stream(T[] array) | 获取当前数组的 Stream 流 |
| 或 |  |
| Stream 提供的方法 | 说明 |
| public static\<T> Stream\<T> of(T...values) | 获取当前接受数据的 Stream 流 |


**示例：**

```java
Collection<String> list = new ArrayList<>();
Collections.addAll(list, "张三", "李四", "王五");
Stream<String> listStream = list.stream();
System.out.println(listStream.count());

// 2. 获取 Map 集合的 Stream 流
Map<String, Integer> map = new HashMap<>();
map.put("牛头", 19);
map.put("马面", 20);
// 2.1 获取键流
Stream<String> keyStream = map.keySet().stream();
// 2.2 获取值流
Stream<Integer> valueStream = map.values().stream();
// 2.1 获取键值对流
Stream<Map.Entry<String, Integer>> entryStream = map.entrySet().stream();

// 3. 获取数组的 Stream 流
String[] names = {"王朝", "马汉", "张龙", "赵虎"};
Stream<String> namesStream1 = Arrays.stream(names);
Stream<String> namesStream2 = Stream.of(names);
```

## 调用流水线方法
| 常用中间方法 | 说明 |
| --- | --- |
| Stream\<T>filter(Predicate<? super T>predicate) | 用于对流中的数据进行过滤 |
| Stream\<T> sorted() | 对元素进行升序排序 |
| Stream\<T> sorted (Comparator<? super T> comparator) | 按照指定规则排序 |
| Stream\<T> limit (long maxSize) | 获取前几个元素 |
| Stream\<T> skip (long n) | 跳过前几个元素 |
| Stream\<T> distinct() | 去除流中重复的元素 |
| \<R> Stream\<R> map(Function<? super T, ? extends R> mapper) | 对元素进行加工，并返回对应的新流 |
| static\<T> Stream\<T> concat (Stream a，Stream b) | 合并 a 和 b 两个流为一个流 |


```java
List<String> list = new ArrayList<>();
Collections.addAll(list, "张三", "李四", "王二麻子");

// 链式调用
list.stream().filter(item -> item.length() < 3).forEach(System.out::println); // 张三 李四
```

## 获取结果
### 中断 Stream 链
调用终结方法后，不再返回新的 Stream 流，也不能继续使用流了。

| Stream 提供的常用终结方法 | 说明 |
| --- | --- |
| void forEach(Consumer action) | 对此流运算后的元素执行遍历 |
| long count() | 统计此流运算后的元素个数 |
| Optional<T> max(Comparator<? super T> comparator) | 获取此流运算后的最大值元素 |
| Optional<T> min(Comparator<? super T> comparator) | 获取此流运算后的最小值元素 |


```java
Optional<Student> max1 = students.stream().max((o1, o2) -> Double.compare(o1.getScore() - o2.getScore()));
Student res = max1.get();

// 简化
Student max2 = students.stream().max((o1, o2) -> Double.compare(o1.getScore() - o2.getScore())).get();
```

### 收集 Stream 流
把 Stream 流操作后的结果转回到集合或者数组中返回。

| Stream 提供的常用终结方式 | 说明 |
| --- | --- |
| R collect(Collector collector) | 把流处理后的结果收集到一个指定的集合中去 |
| Object[] toArray() | 把流处理后的结果收集到一个数组中去 |


| Collectors 工具类提供了具体的收集方式 | 说明 |
| --- | --- |
| public static <T> Collector toList() | 把元素收集到 List 集合中 |
| public static <T> Collector toSet() | 把元素收集到 Set 集合中 |
| public static <T> Collector toMap(Function keyMapper, Function valueMapper) | 把元素收集到 Map 集合中 |


```java
List<String> list = new ArrayList<>();
Collections.addAll(list, "张三", "李四", "王二麻子", "张三");

// 1. 收集到 list
Stream<String> stream1 = list.stream();
List<String> res1 = stream1.filter(item -> item.length() < 3).collect(Collectors.toList());
// JDK 16 开始支持，并且只读不可修改
// List<String> res2 = stream1.filter(item -> item.length() < 3).toList();
System.out.println(res1);

// 2. 收集到 set
Stream<String> stream2 = list.stream(); // stream 只能用一次，用完就要重新创建
Set<String> res3 = stream2.filter(item -> item.length() < 3).collect(Collectors.toSet());
System.out.println(res3);

// 3. 收集到数组
Stream<String> stream3 = list.stream();
Object[] res4 = stream3.filter(item -> item.length() < 3).toArray();
System.out.println(Arrays.toString(res4));

// 4. 收集到 map
List<Student> students = new ArrayList<>();
students.add(new Student("张三", 18));
students.add(new Student("张三", 20));
students.add(new Student("李四", 22));
students.add(new Student("王五", 24));

// 取前三个，将名字作为健，年纪作为值
Map<String, Integer> res5 = students.stream().limit(3).collect(Collectors.toMap(
        // 参数一：映射健；
        Student::getName,
        // 参数二：映射值；
        Student::getAge,
        // 参数三：二分合并操作，避免 Duplicate key 错误，当键名重复时，指定需要的值；
        (v1, v2) -> v1 + v2
));
System.out.println(res5);
```

# 二十、File、IO 流
File 类只能对文件本身进行操作，不能读写文件里面存储的数据。

IO 流用于读写数据。

## File
### 创建 File 类
| 构造器 | 说明 |
| --- | --- |
| public File(String pathname) ★★★ | 根据文件路径创建文件对象 |
| public File(String parent, String child) | 根据父路径和子路径名字创建文件对象 |
| public File(File parent, String child) | 根据父路径对应文件对象和子路径名字创建文件对象 |


```java
// 1. 创建文件
File f1 = new File("E:\\user\\aa.jpg");
// File f2 = new File("E:/user/aa.jpg");
// File f3 = new File("E:" + File.separator + "user" + File.separator + "aa.jpg"";
f1.length(); // 字节个数

// 2. 创建文件夹
File f4 = new File("E:\\user");
f4.length(); // 拿到的是文件夹本身的大小，不是里面所有文件的的大小

// 3. 文件路径可以不存在
File f5 = new File("E:\\nodata");

// 4. 支持绝对路径和相对路径
// 相对路径默认相对到工程目录下寻找
// 一般用来好项目中的资源文件
File f6 = new File("file-module/src/aa.jpg");
```

### 判断类型、获取信息
| 方法名称 | 说明 |
| --- | --- |
| public boolean exists() | 判断当前文件对象，对应的文件路径是否存在，存在返回 true |
| public boolean isFile() | 判断当前文件对象指代的是否是文件，是文件返回true，反之 |
| public boolean isDirectory() | 判断当前文件对象指代的是否是文件夹，是文件夹返回true，反之 |
| public String getName() | 获取文件的名称(包含后缀) |
| public long length() | 获取文件的大小，返回字节个数 |
| public long lastModified() | 获取文件的最后修改时间。 |
| public String getPath() | 获取创建文件对象时，使用的路径 |
| public String getAbsolutePath() | 获取绝对路径 |


### 创建、删除文件
| 创建文件 | 说明 |
| --- | --- |
| public boolean createNewFile() | 创建一个新的空的文件，成功返回 true |
| public boolean mkdir() | 只能创建一级文件夹，成功返回 true |
| public boolean mkdirs()★★★ | 可以创建多级文件夹，成功返回 true |


| 删除文件 | 说明 |
| --- | --- |
| public boolean delete() | 只能删除文件和空文件夹，成功返回 true |


### File 的遍历
| 遍历文件夹 | 说明 |
| --- | --- |
| public Stirng[] list() | 获取当前目录下所有的"一级文件名称"到一个字符串数组中去返回 |
| public File[] listFiles() ★★★ | 获取当前目录下所有的"一级文件对象"到一个文件对象数组中去返回 |


使 **listFiles() **方法时的注意事项：

+ 当主调是文件，或者路径不存在时，返回 null；
+ 当主调是空文件夹时，返回一个长度为 0 的数组；
+ **当主调是一个有内容的文件夹时，将里面所有一级文件和文件夹的路径放在 File 数组中返回；**
+ 当主调是一个文件夹，且里面有隐藏文件时，将里面所有文件和文件夹的路径放在 File 数组中返回，包含隐藏文件；
+ 当主调是一个文件夹，但是没有权限访问该文件夹时，返回 null；

### 搜索与递归
```java
/**
 * 递归文件搜索
 * @param dir      被搜索的文件夹
 * @param fileName 被搜索的文件名
 */
public static void searchFile(File dir, String fileName) {
    if (dir == null || !dir.exists() || dir.isFile()) return;

    File[] files = dir.listFiles();
    if (files == null || files.length == 0) return;
    for (File file : files) {
        if (file.isFile()) {
            if (file.getName().contains(fileName)) {
                System.out.println("已查询到文件路径：" + file.getAbsolutePath());
            }
        } else {
            searchFile(file, fileName);
        }
    }
}
```

## 字符集
### Unicode 字符集
+ Unicode 是国际组织指定的，可以容纳世界上所有文字、符号字符集，也叫统一码或万国码；
+ 字符集是字符的编号；

### ASCⅡ字符集
只有英文、数字、符号，占一个字节。

### GBK 字符集
<font style="color:rgb(51, 51, 51);">汉字内码扩展规范，汉字占2个字节，英文、数字占一个字节。</font>

### UTF-32
+ 4个字节表示一个字符，但是浪费存储空间，通信效率变低。

### UTF-8
+ 是 Unicode 字符集的一种编码方案，采取可变长编码的方式，共分四个长度区：1个字节、2个字节、3个字节、4个字节；
+ 英文字符、数字只占 1 个字节（兼容标准 ASCⅡ 编码）汉字字符占用3个字节；
+ 使用固定前缀方案，区分不同的长度区

| 长度区 | UTF-8 编码格式（二进制） | 实际编码位（去掉固定前缀，实际能用的） |
| --- | --- | --- |
| 1个字节（ASCⅡ 编码） | 0xxxxxxx | 7 |
| 2个字节 | 110xxxxx 10xxxxxx | 11 |
| 3个字节 | 1110xxxx 10xxxxxx 10xxxxxx | 16 |
| 4个字节 | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx | 21 |


示例：<font style="color:rgb(51, 51, 51);">汉”字的统一码编码是 0x6C49。将 0x6C49 写成二进制是：0110(6) 1100(C) 0100(4) 1001(9)，依次放入3字节长度区的模板中，得到：11100110 10110001 10001001，即 E6 B1 89。</font>

### <font style="color:rgb(51, 51, 51);">编码解码</font>
| String 编码 | 说明 |
| --- | --- |
| byte[] getByte() | 使用平台的默认字符集将该 string编码为一系列字节，将结果存储到新的字节数组中 |
| byte[] getBytes(String charsetName) | 使用指定的字符集将该 String 编码为一系列字节，将结果存储到新的字节数组中 |


| String 解码 | 说明 |
| --- | --- |
| String(byte[] bytes) | 通过使用平台的默认字符集解码指定的字节数组来构造新的 String |
| String(byte[]bytes,String charsetName) | 通过指定的字符集解码指定的字节数组来构造新的 String |


## IO 流
`java.io`包

![画板](https://cdn.nlark.com/yuque/0/2025/jpeg/29092218/1745736403488-b0ce3f2e-caea-471d-bb4f-a154cc4cc5b6.jpeg)

### 字节流
适合操作所有类型文件，比如音频、视频、图片、文本文件的复制、转移等。

#### FileInputStrean
以内存为基准，把磁盘文件中的数据以字节的形式读入到内存中。

| 构造器 | 说明 |
| --- | --- |
| public FileInputStream(File file) | 创建字节输入流管道与源文件接通 |
| public FileInputStream(String pathname) ★★★ | 创建字节输入流管道与源文件接通 |


| 实例方法名称 | 说明 |
| --- | --- |
| public int read() | 每次读取一个字节返回，如果发现没有数据可读会返回-1 |
| public int read(byte[] buffer) ★★★ | 每次用一个字节数组去读取数据，返回字节数组读取了多少个字节，如果发现没有数据可读会返回-1 |


```java
InputStream in = new FileInputStream("io-module\\src\\com\\io\\test\\data1.txt");

int index;
while ((index = in.read()) != -1) {
    System.out.print((char) index);
}
```

缺点：1. 性能差；2. 遇到汉字会乱码（每次只读一个字节，汉字是3个字节）



```java
InputStream in = new FileInputStream("io-module\\src\\com\\io\\test\\data1.txt");

byte[] buffer = new byte[3]; // // 指定一次读取3个字节
int len;
while ((len = in.read(buffer)) != -1) {
    String str = new String(buffer, 0, len); // 读多少截取多少，避免上一次多余的数据污染
    System.out.print(str);
}
```

优点：性能好；

缺点：依然可能会出现汉字乱码的问题；



```java
InputStream in = new FileInputStream("io-module\\src\\com\\io\\test\\data1.txt");

// 手动实现
File f = new File("io-module\\src\\com\\io\\test\\data1.txt");
long size = f.length();
byte[] buffer1 = new byte[(int) size]; // 指定文件大小的读取长度，一次性读完

int len = in.read(buffer1);
System.out.println("内容：" + new String(buffer1));
System.out.println("个数：" + len);

// 直接调用 API
byte[] buffer2 = in.readAllBytes();
System.out.println("内容：" + new String(buffer2));
```

#### FileOutputStream
以内存为基准，把内存中的数据以字节的形式写出到文件中去。

| 构造器 | 说明 |
| --- | --- |
| public File0utputstream(File file) | 创建字节输出流管道与源文件对象接通 |
| public File0utputStream(String filepath) | 创建字节输出流管道与源文件路径接通 |
| public File0utputStream(File file, boolean append) | 创建字节输出流管道与源文件对象接通，可追加数据(append 为 true 时追加，默认 false 即覆盖) |
| public File0utputStream(string filepath，boolean append) | 创建字节输出流管道与源文件路径接通，可追加数据(append 为 true 时追加，默认 false 即覆盖) |


| 实例方法名 | 说明 |
| --- | --- |
| public void write(int a) | 写一个字节出去 |
| public void write(byte[] buffer) | 写一个字节数组出去 |
| public void write(byte[]buffer ,int pos ,int len) | 写一个字节数组的一部分出去 |
| public void close() throws IOException | 关闭流（包含 flush 刷新） |


```java
OutputStream out = new FileOutputStream(
    // 路径
    "io-module\\src\\com\\io\\test\\data2.txt",
    // 是否追加（默认覆盖）
    true
);

// 1. 写字节数据进去
out.write('a');
out.write(98);
// out.write('徐'); // 只取一个字节，会导致乱码
out.write("\r\n".getBytes());

// 2. 写一个字节数组进去
byte[] bytes = "cdef我尼玛".getBytes();
out.write(bytes);
out.write("\r\n".getBytes());
out.write(bytes, 4, 3 * 3); // 从 4 号索引位置开始写入，总共写9个字节的长度（这里有3个汉字，一个汉字3个字节）

// 关闭流
// out.flush(); close 已包含 flush
out.close();
```

```java
/**
 * 复制文件
 * @param inputPathName 源文件地址
 * @param outputPathName 目标文件地址
 */
public static void copyFile(String inputPathName, String outputPathName) {
    InputStream input = null;
    OutputStream output = null;
    try {
        // 创建字节输入流管道与源文件连通
        input = new FileInputStream(inputPathName);
        // 创建字节输出流管道与目标文件连通
        output = new FileOutputStream(outputPathName);

        // 准备一个字节数组
        byte[] buffer = new byte[1024];

        // 转移数据
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        System.out.println("Copied");

    } catch(Exception e) {
        e.printStackTrace();
    } finally {
        // 手动释放资源
        try {
            if(input != null) input.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if(output != null) output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

//
copyFile(
    "io-module\\src\\com\\io\\test\\data2.txt",
    "io-module\\src\\com\\io\\test\\data2_cp.txt"
);
```

#### 资源释放
JDK 8 开始使用`try-with-resource`提供了更简单的资源释放方案。

**格式：**

> try (定义资源1; 定义资源2; ......) {
>
> // 可能出现异常的代码
>
> } catch(异常类名 变量名) {
>
> // 异常的处理代码
>
> }
>

**注意事项：**

+ 定义的资源使用完毕后，会自动调用其 `close()`方法，完成对资源的释放；
+ try() 中只能放置资源，否则报错；
+ 资源一般指的是最终实现了`AutoCloseable`接口：

```java
public abstract class InputStream implements Closable {}

public abstract class OutputStream implements Closable, Flushable {}

public interface Closeable extends AutoClosable {}
```

```java
public static void copyFile(String inputPathName, String outputPathName) {
    try (
            // try 中只能放置资源对象，用完会自动调用 close 方法释放资源
            // 创建字节输入流管道与源文件连通
            InputStream input = new FileInputStream(inputPathName);
            // 创建字节输出流管道与目标文件连通
            OutputStream output = new FileOutputStream(outputPathName);
    ) {
        // 准备一个字节数组
        byte[] buffer = new byte[1024];

        // 转移数据
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        System.out.println("Copied");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

### 字符流
只适合操作纯文本文件，比如读写 .txt、.java 文件等。

#### FileReader
以内存为基准，可以把文件中的数据以字符的形式读入到内存中去。

可以避免字符乱码。

| 构造器 | 说明 |
| --- | --- |
| public FileReader(File file) | 创建字符输入流管道与源文件接通 |
| public FileReader(string pathname) | 创建字符输入流管道与源文件接通 |


| 方法名称 | 说明 |
| --- | --- |
| public int read() | 每次读取一个字符，返回对应的 Unicode 编号，如果发现没有数据可读会返回-1 |
| public int read(char[] buffer) | 每次用一个字符数组去读取数据，返回字符数组读取了多少个字符如果发现没有数据可读会返回-1 |


```java
try (
        Reader reader = new FileReader("io-module\\src\\com\\io\\test\\data1.txt");
) {
    char[] buffer = new char[3];
    int len;
    while ((len = reader.read(buffer)) != -1) {
        System.out.print(new String(buffer, 0, len));
    }
} catch (Exception e) {
    throw new RuntimeException(e);
}
```

#### FileWriter
以内存为基准，把内存中的数据以字符的形式写出到文件中去。

| 构造器 | 说明 |
| --- | --- |
| public FileWriter(File file) | 创建字节输出流管道与源文件对象接通 |
| public FileWriter(string filepath) | 创建字节输出流管道与源文件路径接通 |
| public FileWriter(File file, boolean append) | 创建字节输出流管道与源文件对象接通，可追加数据 |
| public FileWriter(string filepath, boolean append) | 创建字节输出流管道与源文件路径接通，可追加数据 |


| 方法名称 | 说明 |
| --- | --- |
| void write(int c) | 写一个字符 |
| void write(String str) | 写一个字符串 |
| void write(String str, int off, int len) | 写一个字符串的一部分 |
| void write(char[] cbuf) | 写入一个字符数组 |
| void write(char[] cbuf, int off, int len) | 写入字符数组的一部分 |


```java
public static void main(String[] args) {
    try (
            Writer writer = new FileWriter(
                    "io-module\\src\\com\\io\\test\\writer_out.txt",
                    true
            );
    ) {
        writer.write(97);
        writer.write("b");
        writer.write("\r\n");

        writer.write("abc我爱你中国def");
        writer.write("\r\n");

        writer.write("abc我爱你中国def", 3, 3);
        writer.write("\r\n");

        char[] chars = "abc我爱你中国def".toCharArray();
        writer.write(chars);
        writer.write("\r\n");

        writer.write(chars, 6, 2);
        writer.write("\r\n");

    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
```

### 缓冲流
#### 字节缓冲输出/输入流
+ 对原始流进行包装，以提高原始流（字节流、字符流）读写数据的性能；
+ 字节缓冲输出/输入流自带了 8KB 缓冲池；

| 字节缓冲输出/输入流 构造器 | 说明 |
| --- | --- |
| public BufferedInputStream(InputStream is) | 把低级的字节输入流包装成一个高级的缓冲字节输入流，从而提高读数据的性能 |
| public BufferedOutputStream(0utputStream os) | 把低级的字节输出流包装成一个高级的缓冲字节输出流，从而提高写数据的性能 |


```java
InputStream input = new FileInputStream(inputPathName);
InputStream bufferInput = new BufferedInputStream(input);
```

#### 字符缓冲输入/输出流
+ 字符缓冲输入流自带 8K（8192）的字符缓冲池；

| 构造器 | 说明 |
| --- | --- |
| public BufferedReader(Reader r) | 把低级的字符输入流包装成字符缓冲输入流管道，提高字符输入流读字符数据的性能 |
| public BufferedWriter(Writer r) | 把低级的字符输出流包装成字符缓冲输出流管道，提高字符输出流读字符数据的性能 |


| **BufferedReader 新增的功能** | **说明** |
| --- | --- |
| public String readLine() | 读取一行数据返回，如果没有数据可读，返回 null |
|  |  |
| **BufferedWriter 新增功能** | **说明** |
| public void newLine() | 换行（和 "\r\n" 效果一样） |


```java
try (
        Reader reader = new FileReader("io-module\\src\\com\\io\\test\\data3.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
) {
    String line;
    while ((line = bufferedReader.readLine()) != null) {
        System.out.println(line);
    }
} catch (Exception e) {
    throw new RuntimeException(e);
}
```

#### 如何提高字节流读写性能
缓冲池扩容 16~32 KB（再大性能提升就不明显了），可提高读写性能。

```java
public static void copyFile(String inputPathName, String outputPathName) {
    try (
            InputStream is = new FileInputStream(inputPathName);
            InputStream bis = new BufferedInputStream(is);

            OutputStream os = new FileOutputStream(outputPathName);
            OutputStream bos = new BufferedOutputStream(os);
    ) {
        // 准备一个字节数组 16 或 32KB 最佳
        byte[] buffer = new byte[1024 * 32];
        int length;
        while ((length = bis.read(buffer)) > 0) {
            bos.write(buffer, 0, length);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

### 转换流
+ **问题：**如果代码编码和被读取的文件文本的编码不一致，使用字符流读取文本文件时会出现乱码。
+ **解决：**先获取文件的原始字节流，再按真实的字符集编码转成字符输入流。

#### 字符输入转换流
| 构造器 | 说明 |
| --- | --- |
| public InputStreamReader(InputStream is) | 把原始的字节输入流按照代码默认编码转成字符输入流（与直接用 FileReader 效果一样） |
| public InputStreamReader(InputStream is, String charset) ★★★ | 把原始的**字节输入流**按照指定编码**转成字符输入流** |


```java
public static void main(String[] args) {
    try (
            // 1. 得到 GBK 文件的原始字节输入流
            InputStream isr = new FileInputStream("io-module\\src\\com\\io\\test\\data4_gbk.txt");
            // 2. 通过字符输入转换流把原始字节输入流按照指定编码格式转成字符输入流
            Reader reader = new InputStreamReader(isr, "GBK");
            // 3. 再使用高级缓冲输入流包装
            BufferedReader br = new BufferedReader(reader)
    ) {
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
```

#### 字符输出转换流
| 构造器 | 说明 |
| --- | --- |
| public OutputStreamWriter(OutputStream os) | 把原始的字节输出流按照代码默认编码转成字符输出流 |
| public OutputStreamWriter(OutputStream os, String charset) ★★★ | 把原始的**字节输出流**按照指定编码**转成字符输出流** |


```java
public static void main(String[] args) {
    try(
            OutputStream fos = new FileOutputStream("io-module\\src\\com\\io\\test\\data4_gbk_cp.txt");
            Writer osw = new OutputStreamWriter(fos, "GBK");
            BufferedWriter bos = new BufferedWriter(osw);
    ) {
        bos.write("好事总得善人做，");
        bos.newLine();
        bos.write("哪有凡人做神仙！");
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
```

### 打印流
打印流可以更方便、更高效的打印数据，能实现打印啥就是啥。

#### 字节打印流
| PrintStream 构造器 | 说明 |
| --- | --- |
| public PrintStream(OutputStream/File/String) | 打印流直接通向字节输出流/文件/文件路径 |
| public PrintStream(String fileName, Charset charset) | 可以指定写出去的字符编码 |
| public PrintStream(OutputStream out, boolean autoFlush) | 可以指定实现自动刷新 |
| public PrintStream(OutputStream out, boolean autoFlush, String encoding) | 可以指定实现自动刷新，并可以指定字符编码 |


| 方法 | 说明 |
| --- | --- |
| public void println(Xxx xxx) | 打印任意类型的数据出去 |
| public void write(int/byte[]/byte[]一部分) | 支持写字节数据出去 |


#### 字符打印流
| PrintWriter 构造器 | 说明 |
| --- | --- |
| public PrintWriter(outputstream/Writer/File/string) | 打印流直接通向字节输出流/文件/文件路径 |
| public PrintWriter(String fileName,Charset charset) | 可以指定写出去的字符编码 |
| public PrintWriter(OutputStream out/Writer,boolean autoFlush) | 可以指定实现自动刷新 |
| public PrintWriter(OutputStream out,boolean autoFlush, String encoding) | 可以指定实现自动刷新，并可指定字符的编码 |


| 方法 | 说明 |
| --- | --- |
| public void println(Xxx xxx) | 打印任意类型的数据出去 |
| public void write(int/string/char[]/..) | 可以支持写字符数据出去 |


```java
// 用高级管道包装低级管道
PrintWriter ps = new PrintWriter(new FileWriter("pathname", true));
```

#### 输出语句重定向
```java
try (
        PrintStream ps = new PrintStream(new FileOutputStream("io-module\\src\\com\\io\\test\\info.txt", true));
) {
    System.setOut(ps);
    System.out.println("将系统打印的输出语句从控制台重定向到指定的文件中");
} catch (Exception e) {
    throw new RuntimeException(e);
}
```

### 数据流
#### 数据输出流
允许把数据和其类型一并写出去

| 构造器 | 说明 |
| --- | --- |
| public DataOutputStream(OutputStream out) | 创建新数据输出流包装基础的字节输出流 |


| 方法 | 说明 |
| --- | --- |
| public final void writeByte(int v)throws IOException | 将byte类型的数据写入基础的字节输出流 |
| public final void writeInt(int v) throws IOException | 将int类型的数据写入基础的字节输出流 |
| public final void writeDouble(Double v)<br/>throws IOException | 将double类型的数据写入基础的字节输出流 |
| public final void writeUTF(string str)throws IOException | 将字符串数据以UTF-8编码成字节写入基础的字节输出流 |
| public void write(int/byte[]/byte[]-部分) | 支持写字节数据出去 |


#### 数据输入流
| 构造器 | 说明 |
| --- | --- |
| public DataInputstream(Inputstream is) | 创建新数据输出流包装基础的字节输出流 |


| 方法 | 说明 |
| --- | --- |
| public final byte readByte()throws IOException | 读取字节数据返回 |
| public final int readInt()throws IException | 读取int类型的数据返回 |
| public final double readDouble() throws IOException | 读取double类型的数据返回 |
| public final string readuTF()throws IOException | 读取字符串数(UTF-8)据返回 |
| public int readInt()/read(byte[]) | 支持读字节数据进来 |


### 序列化流
#### 序列化
<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">序列化是指将</font>**<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">对象的状态（数据）转换为字节流</font>**<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">，以便：</font>

+ **<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">存储到文件</font>**<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">（持久化）；</font>
+ **<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">通过网络传输</font>**<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">（如 RPC、分布式系统）；</font>
+ **<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">在内存中缓存</font>**<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">（如 Session 复制）；</font>

<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">反序列化则是将字节流重新转换为对象。</font>

#### <font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">对象字节输入/输出流</font>
| 对象字节输出流构造器 | 说明 |
| --- | --- |
| public ObjectOutputStream(OutputStream out) | 创建对象字节输出流，包装基础的字节输出流 |


| 对象字节输出流方法 | 说明 |
| --- | --- |
| public final void writeObject(Object o) throws IOException | 把对象写出去 |


| 对象字节输入流构造器 | 说明 |
| --- | --- |
| public ObjectInputStream(InputStream out) | 创建对象字节输入流，包装基础的字节输入流 |


| 对象字节输入流方法 | 说明 |
| --- | --- |
| public final Object readObject() | 把存储在文件中的 Java 对象读取出来 |


#### Serialzable 接口
`Serializable` 是 Java 中的一个**标记接口（Marker Interface）**，它本身没有任何方法或字段，仅用于标识一个类的对象可以被 **序列化（Serialization）**。

<font style="background-color:rgb(252, 252, 252);">参与序列化的对象必须实现 </font>`Serialzable` 接口。

#### transient 关键字
修饰的成员变量将不参与序列化。

#### 序列化多个对象
用一个 ArrayList 集合存储多个对象，然后直接对集合进行序列化。其中，ArrayList 集合已经实现了 `Serialzable`接口。

### IO 框架
#### 使用步骤
1. 在`module`中新建 `lib`文件夹；
2. 在 `lib`中复制 jar 包；
3. 右击点击 `Add as Library`添加到项目库。

#### **<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">Apache Commons IO</font>**
| FileUtils类提供的部分方法展示 | 说明 |
| --- | --- |
| public static void copyFile(File srcFile, File destFile) | 复制文件 |
| public static void copyDirectory(File srcDir, File destDir) | 复制文件夹 |
| public static void deleteDirectory(File directory) | 删除文件夹 |
| public static String readFileTostring(File file, String encoding) | 读数据 |
| public static void writestringToFile(File file, String data, String charname, boolean append)  | 写数据 |


```java
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        // 拷贝文件
        FileUtils.copyFile(
            new File("io-module\\src\\com\\io\\test\\data7.txt"),
            new File("io-module\\src\\com\\io\\test\\data7_cp.txt")
        );
    }
}

```

#### 原生 copy 方法
| IOUtils 类提供的部分方法展示 | 说明 |
| --- | --- |
| public static int copy(InputStream inputstream, OutputStream outputStream) | 复制文件 |
| public static int copy(Reader reader, Writer writer) | 复制文件 |
| public staticvoid write(String data, Outputstream output, String charsetName) | 写数据 |


```java
import java.io.File;

Files.copy(
        Path.of("io-module/src/com/io/test/data7.txt"),
        Path.of("io-module/src/com/io/test/data7_cp.txt")
);
```

# 二十一、特殊文件与日志
## properties 属性文件
+ `Properties`是一个 Map 集合（键值对集合），但是我们一般不会当集合使用；
+ 核心作用：Properties 是用来代表属性文件的，通过 Properties 读写属性文件里的内容；

| 构造器 | 说明 |
| --- | --- |
| public Properties() | 用于构建 Properties 集合对象 |


| 常用方法 | 说明 |
| --- | --- |
| public void load(InputStream is) | 通过字节输入流，读取属性文件里的键值对数据 |
| public void load(Reader reader) | 通过字符输入流，读取属性文件里的键值对数据 |
| public String getProperty(String key) | 根据键获取值（get 方法） |
| public Set<String> stringPropertyNames() | 获取全部键的集合（keySet 方法） |
| public Object setProperty(String key, String value) | 保存键值对数据到 Properties 对象中去 |
| public void store(OutputStream os, String comments) | 把键值对数据，通过字节输出流写到属性文件里去 |
| public void store(Writer w, String comments) | 把键值对数据，通过字符输出流写到属性文件里去 |


```java
// 创建容器
Properties props = new Properties();
// 加载属性文件到容器中
props.load(new FileInputStream("property-module\\src\\data.properties"));
System.out.println("props = " + props);

// 遍历
props.forEach((key, value) -> {
    System.out.println(key + " = " + value);
});

// 取值
System.out.println(props.get("name"));
Set<String> keys = props.stringPropertyNames();
for (String key : keys) {
    System.out.println(key + ": " + props.getProperty(key));
}

// 存值
props.setProperty("gender", "male");
props.store(
        new FileOutputStream("property-module\\src\\data.properties"),
        "新增属性"
);
```

## XML文件
可扩展标记语言，本质是一种数据格式，可用来存储复杂的数据结构和数据关系。

常用来做系统的配置文件。

### 语法规则
+ XML 文件的后缀名为 `xml`，文档声明必须是第一行；

```xml
<?xml version='1.0' encoding="UTF-8" ?>
```

+ XML 中的特殊字符；

| 原义文本字符 | XML 转义文本字符 | 说明 |
| --- | --- | --- |
| < | &lt; | 小于 |
| > | &gt; | 大于 |
| & | &amp; | 与 |
| ' | &apos; | 单引号 |
| " | &quot; | 双引号 |


```xml
<?xml version='1.0' encoding="UTF-8" ?>
<users>
  <user>
    <name>张三</name>
    <gender>男</gender>
    <sql>
      select * from tb_student where age &gt;= 18 &amp;&amp; age &lt;= 25
    </sql>
    </user>
</users>
```

+ XML 中也可以写一个叫 CDATA 的数据区，`<![CDATA[...内容]]>`，里面的内容可以随便写。

```xml
<?xml version='1.0' encoding="UTF-8" ?>
<sql>
  <![CDATA[
    select * from tb_student where age >= 18 && age <= 25
  ]]>
</sql>

```

### Dom4j 解析 XML
[下载地址](https://dom4j.github.io/)

SAXReader：Dom4j 提供的解析器

| SAXReader 构造器和方法 | 说明 |
| --- | --- |
| public SAXReader() | 构建Dom4]的解析器对象 |
| public Document read(String url) | 把XML文件读成Document对象 |
| public Document read(InputStream is) | 通过字节输入流读取XML文件 |


| Document 方法名 | 说明 |
| --- | --- |
| Element getRootElement() | 获得根元素对象 |


```java
import org.dom4j.io.SAXReader;
import org.dom4j.Document;

SAXReader saxReader = new SAXReader();

Document document = saxReader.read("xxxxx");

Element rootElement = document.getRootElement();
System.out.println(rootElement.getName());
```

![](https://cdn.nlark.com/yuque/0/2025/png/29092218/1745853066170-506237c9-3e0f-4108-bd41-6d581982e9ba.png)

### 写入 XML
不建议使用 Dom4j，推荐直接把程序里的数据拼接成 XML 格式，然后用 IO 流写出去。

```java
StringBuilder sb = new StringBuilder();
sb.append("<?xml version='1.0' encoding="UTF-8" ?>\r\n");
sb.append("<user>\r\n");
sb.append("\t<name>").append("张三").append("</name>\r\n");
sb.append("</user>\r\n");

PrintStream ps = new PrintStream("xxxxx.xml");
ps.println(sb);
ps.close();
```

### 约束 XML 文件的书写
使用**约束文档**限制 XML 文件只能按照某种格式进行书写。

约束文档的分类：DTD 文档、Schema 文档。

## 日志
日志框架：JUL、Log4j、<font style="color:#DF2A3F;">Logback</font>等；

日志接口：Commons Logging（JCL）、<font style="color:#DF2A3F;">Simple Logging Facade for Java（SLF4J）</font>；

### Logback 日志框架
Logback 是基于 SLF4J 的日志规范实现的框架。

Logback 日志框架官方网站：[https://logback.qos.ch](https://logback.qos.ch/)

有 `logback-core`<font style="color:rgb(0, 0, 0);">、</font>`logback-classic`<font style="color:rgb(0, 0, 0);">、</font>`logback-access`<font style="color:rgb(0, 0, 0);">三个模块。</font>

**<font style="color:rgb(0, 0, 0);">实现步骤：</font>**

1. 导入 Logback 框架到项目中去；
2. 将 Logback 框架核心配置文件 `logback.xml`直接拷贝到 `src`目录下（必须是 src）
3. 创建 Logback 框架提供的 Logger 对象，然后用 Logger 对象调用其提供的方法就可以记录系统的日志信息。

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
    // 创建一个 Logback 框架的 Logger 日志对象
    public static final Logger LOGGER = LoggerFactory.getLogger("Test.class");
    public static void main(String[] args) {
        try{
            LOGGER.info("开始记录");
            division(1, 0);
            LOGGER.info("执行结束");
        } catch (Exception e) {
            LOGGER.error("错误日志：" + e.getMessage());
        }
    }
    public static void division (int a, int b) {
        LOGGER.debug("调试日志：" + a + "/" + b);
        int c = a / b;
        LOGGER.info("计算结果： " + c);
    }
}
```

### logback.xml 配置文件
+ 对 Logback 日志框架进行控制；
+ 通常可以设置2个输出日志的位置：控制台、系统文件；

```xml
<!-- 控制台 -->
<appender name="C0NS0LE" class="ch.qos.logback.core.ConsoleAppender">
  <!--输出流对象 默认 System out 改为 System err -->
  <target>System.out</target>
  <encoder>
    <!-- 格式化输出: %d 表示日期，%thread 表示线程名，%-5level: 级别从左显示5个字符宽度，%msg: 日志消息，%m是换行符 -->
    <pattern>%d{yyyy--dd HH:mm:ss.SSS} [%-5level] %c [%thread] : %msg%n</pattern>
  </encoder>
</appender>

<!-- 系统文件 -->
<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
  <encoder>
    <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger {36} - %msg%n</pattern>
    <charset>utf-8</charset>
  </encoder>
  <!--日志输出路径 -->
  <file>D:/log/debug-data.log</file>
  <!--指定日志文件拆分和压缩规则 -->
  <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
    <!--通过指定压缩文件名称，来确定分割文件方式-->
    <fileNamePattern>D:/log/debug-data-%i-%d{yyyy-MM-dd}.log.gz</fileNamePattern>
    <!--文件拆分大小-->
    <maxFileSize>1MB</maxFileSize>
  </rollingPolicy>
</appender>
```

```xml
<!-- all：打印所有，error：只打印错误，off：关闭日志 -->
<root level="all">
  <appender-ref ref="CONSOLE"/>
  <appender-ref ref="FILE"/>
</root>
```

### 日志级别
| 日志级别 | 说明 |
| --- | --- |
| trace | 追踪，指明程序运行轨迹，很少用 |
| debug | 调试，一般作为最低级别 |
| info | 输出重要的运行信息，数据连接、网络连接、IO操作等 |
| warn | 警告 |
| error | 错误 |


# 二十二、多线程
<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">进程是操作系统进行资源分配和调度的基本单位。</font>

<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">而线程是进程中的一个执行单元，是CPU调度和分派的基本单位。</font>

## 创建线程
### 继承 Thread 类
**注意事项：**

+ 子线程的执行需要调用 `start()`开启（会自动执行 `run()`方法），否则不会注册子线程，仅仅是执行 `run()`方法
+ 子类继承 Thread ，无法继承其他类，不利于功能扩展
+ 不要把主线程任务放在启动子线程之前，否则主线程跑完才会执行子线程，相当于还是一个单线程。

```java
public class Test {
    // main 方法本身是由一条主线程执行的
    public static void main(String[] args) {
        // 创建线程对象
        Thread thread = new MyThread();
        // 启动线程，自动执行 run() 方法
        thread.start();

        // 主线程的程序放在后面
        for (int i = 0; i < 4; i++) {
            System.out.println("主线程i = " + i);
        }
    }
}

class MyThread extends Thread {
    // 申明线程要干的事情
    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println("子线程i = " + i);
        }
    }
}
```

### 实现 Runnable 接口
**注意事项：**

+ 创建 Runnable 任务对象，传递给 Thread 处理

```java
public class RunnableDemo {
    public static void main(String[] args) {
        Runnable target = new MyRunnable();
        Thread thread = new Thread(target);
        thread.start();

        for (int i = 0; i < 4; i++) {
            System.out.println("主线程i = " + i);
        }
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println("子线程i = " + i);
        }
    }
}
```

```java
// 简写
Thread thread1 = new Thread(new Runnable() {
    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println("子线程i = " + i);
        }
    }
});

Thread thread2 = new Thread(() -> {
    for (int i = 0; i < 4; i++) {
        System.out.println("子线程i = " + i);
    }
});
```

### 实现 Callable 接口
**注意事项：**

+ 前两种方式重写的`run()`方法无法返回结果；
+ 使用 `Callable`接口和`FutureTask`类可以返回线程执行完毕后的结果；
+ `FutureTask`类是一个 Runnable 对象，可以使用`get()`获取执行线程后的结果。

```java
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableDemo {
    public static void main(String[] args) {
        // 2. 创建 Callable 对象
        Callable<String> call = new MyCallable(50);
        // 3. 封装成 FutureTask 对象
        //    FutureTask 是一个 Runnable 对象
        //    FutureTask 可以获取线程执行后的结果
        FutureTask<String> task = new FutureTask<>(call);
        // 4. 把 FutureTask 交给 Thread 线程对象
        Thread thread = new Thread(task);
        thread.start();

        // 5. 使用 FutureTask 的 get 方法获取结果
        try{
            String sum = task.get();
            System.out.println(sum);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

// 1. 定义任务类
class MyCallable implements Callable<String> {
    private final int n;

    public MyCallable(int n) {
        this.n = n;
    }

    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i;
        }
        return "子线程求合结果：" + sum;
    }
}
```

## 线程 API
| Thread提供的常见构造器 | 说明 |
| --- | --- |
| public Thread(String name) | 可以为当前线程指定名称 |
| public Thread(Runnable target) | 封装 Runnable 对象成为线程对象 |
| public Thread(Runnable target, string name) | 封装 Runnable 对象成为线程对象，并指定线程名称 |


| Thread 提供的常用方法 | 说明 |
| --- | --- |
| public void run() | 线程的任务方法 |
| public void start() | 启动线程 |
| public string getName() | 获取当前线程的名称，线程名称默认是Thread-索引 |
| public void setName(String name) | 为线程设冒名称 |
| public static Thread currentThread() | 获取当前执行的线程对象 |
| public static void sleep(long time) | 让当前执行的线程休眠多少毫秒后，再继续执行 |
| public final void join() | 让调用当前这个方法的线程先执行完 |


```java
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new MyThread("1号线程");
        thread.start();
        System.out.println(thread.getName()); // Thread-0，当前线程的名称，默认 Thread-{index}

        // 主线程任务：
        Thread t = Thread.currentThread(); // 哪个线程在执行，就会得到哪个线程对象
        System.out.println(t.getName()); // 当前获取到主线程的名字 main
        for (int i = 0; i < 4; i++) {
            System.out.println("主线程i = " + i);

            // 满足特定条件时，让子线程插队执行
            if (i == 1) {
                thread.join();
            }
        }
    }
}   

class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }
    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println(Thread.currentThread().getName()+ " 子线程的输出" + i);

            // 线程 sleep 延时
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```

## 线程安全
多个线程同时操作一个共享资源时，可能会出现业务安全问题。

### 线程同步与加锁
每次只允许一个线程加锁，加锁后才能进入访问，访问完毕后自动解锁，然后其他线程才能再加锁进来。

#### 方式1、同步代码块
+ [Demo 案例地址](https://github.com/Zhangyao719/java-study/tree/main/thread-module/src/com/thread/safe)
+ 把访问共享资源的核心代码上锁，以保证线程安全；
+ 对于当前同时执行的线程来说，同步锁必须是同一把（同一个对象），否则会出 bug；
+ 语法：`synchronized(key) {}` 
+ <font style="color:#DF2A3F;">建议使用共享资源作为锁对象，对于实例方法建议使用</font>`<font style="color:#DF2A3F;">this</font>`<font style="color:#DF2A3F;">作为锁对象；</font>
+ 对于静态方法建议使用<font style="color:#DF2A3F;">字节码（类名.class）</font>对象作为锁对象；

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    private String cardNo;
    private double balance;

    // 取款
    public void withdraw(double money) {
        String name = Thread.currentThread().getName();
        synchronized (this) {
            if (balance >= money) {
                System.out.println(name + "取款成功");
                this.balance -= money;
                System.out.println(name + "取款后，账户余额： " + balance);
            } else {
                System.out.println("余额不足！");
            }
        }
    }

    public static void test() {
        // 如果是静态方法，建议使用类名加锁
        synchronized (BankAccount.class) {
            // ......
        }
    }
}
```

#### 方式2、同步方法
+ 使用 `synchronized`关键字给方法上锁；
+ 同步方法其实底层也是有隐式锁对象的，只是锁的范围是整个方法代码；
+ 如果方法是实例方法，同步方法默认用 `this`作为锁对象；
+ 如果方法是静态方法，同步方法默认用 `类名.class`作为锁对象；

#### 方式3、Lock 锁
+ JDK5 开始提供的一个新的锁定操作，通过它可以创建出锁对象进行加锁和解锁，更灵活，更强大；
+ Lock 是接口，不能直接实例化，可以采用它的实现类 `ReentrantLock`来构建 Lock 锁对象；

| Lock 构造器 | 说明 |
| --- | --- |
| public ReentrantLock() | 获得 Lock 锁的实现类对象 |


| Lock 方法 | 说明 |
| --- | --- |
| void lock() | 获得锁 |
| void unlock() | 释放锁 |


```java
@Data
@NoArgsConstructor
public class BankAccount {
    private String cardNo;
    private double balance;
    private final Lock lk = new ReentrantLock(); // 锁对象

    public BankAccount(String cardNo, double balance) {
        this.cardNo = cardNo;
        this.balance = balance;
    }

    public void withdraw(double money) {
        String name = Thread.currentThread().getName();

        lk.lock(); // 加锁
        try {
            if (balance >= money) {
                System.out.println(name + "取款成功");
                balance -= money;
                System.out.println(name + "取款后，账户余额： " + balance);
            } else {
                System.out.println("余额不足！");
            }
        } finally {
            lk.unlock(); // 解锁
        }
    }
}
```

### 线程通信
当多个线程共同操作共享资源时，线程间通过某种方式互相告知自己的状态，以相互协调，并避免无效的资源争夺。

| Object 类等待与唤醒的实例方法 | 说明 |
| --- | --- |
| void wait() | 让当前线程等待并释放所占锁，直到另一个线程调用 notify() 方法或 notifyAll() 方法 |
| void notify() | 唤醒正在等待的单个线程 |
| void nofityAll() | 唤醒正在等待的所有线程 |


## 线程池
+ 用户发起请求，后台会创建一个新的线程，请求过多会产生大量的线程，严重影响系统性能；
+ 线程池是一个可以复用线程的技术；

### 创建线程池
JDK5 提供了代表线程池的接口 `ExecutorService`。

#### 注意事项
1. 临时线程什么时候创建？

新任务提交时，核心线程都在忙，任务队列也满了，并且还可以创建临时线程，此时才会创建临时线程。

2. 什么时候开始拒绝新任务？

核心线程和临时线程都在忙，任务队列也排满，新的任务过来时才会拒绝执行新任务。

#### ThreadPoolExcutor
使用 `ExecutorService`的实现类 `ThreadPoolExcutor`自创建一个线程池对象；

| ThreadPoolExcutor 构造器 | 说明 |
| --- | --- |
| [**<font style="color:rgb(74, 103, 130);background-color:rgb(238, 238, 239);">ThreadPoolExecutor</font>**](https://www.runoob.com/manual/jdk11api/java.base/java/util/concurrent/ThreadPoolExecutor.html#%3Cinit%3E(int,int,long,java.util.concurrent.TimeUnit,java.util.concurrent.BlockingQueue,java.util.concurrent.ThreadFactory,java.util.concurrent.RejectedExecutionHandler))<font style="color:rgb(53, 56, 51);background-color:rgb(238, 238, 239);">(int corePoolSize, int maximumPoolSize, long keepAliveTime, </font>[<font style="color:rgb(74, 103, 130);">TimeUnit</font>](https://www.runoob.com/manual/jdk11api/java.base/java/util/concurrent/TimeUnit.html)<font style="color:rgb(53, 56, 51);background-color:rgb(238, 238, 239);"> unit, </font>[<font style="color:rgb(74, 103, 130);">BlockingQueue</font>](https://www.runoob.com/manual/jdk11api/java.base/java/util/concurrent/BlockingQueue.html)<font style="color:rgb(53, 56, 51);background-color:rgb(238, 238, 239);"><</font>[<font style="color:rgb(74, 103, 130);">Runnable</font>](https://www.runoob.com/manual/jdk11api/java.base/java/lang/Runnable.html)<font style="color:rgb(53, 56, 51);background-color:rgb(238, 238, 239);">> workQueue, </font>[<font style="color:rgb(74, 103, 130);">ThreadFactory</font>](https://www.runoob.com/manual/jdk11api/java.base/java/util/concurrent/ThreadFactory.html)<font style="color:rgb(53, 56, 51);background-color:rgb(238, 238, 239);"> threadFactory, </font>[<font style="color:rgb(74, 103, 130);">RejectedExecutionHandler</font>](https://www.runoob.com/manual/jdk11api/java.base/java/util/concurrent/RejectedExecutionHandler.html) handler) | 使用给定的初始参数创建新的 `ThreadPoolExecutor` |


其中参数说明如下：

| ThreadPoolExcutor 构造器参数 | 类型 | 说明 | 理解 |
| --- | --- | --- | --- |
| <font style="color:rgb(53, 56, 51);">corePoolSize</font> | <font style="color:rgb(53, 56, 51);">int</font> | 指定线程池的核心线程的数量 | 正式工 |
| maximumPoolSize | int | 指定线程池的最大线程数量 | 最大员工（正式工+临时工） |
| keepAliveTime | long | 指定临时线程的存活时间 | 临时工空闲多久开除 |
| unit | TimeUnit | 指定临时线程存活的时间单位(秒、分、时、天) |  |
| workOueue | BlockingQueue<Runnable> | 指定线程池的任务队列 | 客人排队的地方 |
| threadFactory | ThreadFactory | 指定线程池的线程工厂 | 负责招聘员工的hr |
| handler | RejectedExecutionHandler | 指定线程池的任务拒绝策略(线程都在忙，任务队列也满了的时候，新任务来了该怎么处理) | 忙不过来怎么办 |


| 新任务拒绝策略 | 说明 |
| --- | --- |
| ThreadPoolExecutor.AbortPolicy | 丢弃任务并抛出 RejectedExecutionException 异常。是默认的策略 |
| ThreadPoolExecutor.DiscardPolicy | 丢弃任务，但是不抛出异常 这是不推荐的做法 |
| ThreadPoolExecutor.DiscardOldestPolicy | 抛弃队列中等待最久的任务 然后把当前任务加入队列中 |
| ThreadPoolExecutor.CallerRunsPolicy | 由主线程负责调用任务的 run() 方法从而绕过线程池直接执行 |


| ExecutorService 的常用方法 | 说明 |
| --- | --- |
| void execute(Runnable command) | 执行 Runnable 任务 |
| Future<T>submit(Callable<T>task) | 执行 Callable 任务，返回未来任务对象，用于获取线程返回的结果 |
| void shutdown() | 等全部任务执行完毕后，再关闭线程池 |
| List<Runnable>shutdownNow() | 立刻关闭线程池，停止正在执行的任务，并返回队列中未执行的任务 |


```java
ExecutorService pool = new ThreadPoolExecutor(
    3, // 3个核心线程
    5, // 最大5个线程
    1, // 临时线程存活 1 分钟
    TimeUnit.MINUTES, // 临时线程存活 1 分钟
    new ArrayBlockingQueue<>(3), // 队列中等待的任务最多三个
    Executors.defaultThreadFactory(),
    new ThreadPoolExecutor.AbortPolicy()
);

Runnable target = new MyRunnable();
pool.execute(target); // 自动创建核心线程1，处理任务
pool.execute(target); // 自动创建核心线程2，处理任务
pool.execute(target); // 自动创建核心线程3，处理任务
pool.execute(target); // 进队列
pool.execute(target); // 进队列
pool.execute(target); // 进队列
pool.execute(target); // 临时线程4开始处理任务
pool.execute(target); // 临时线程5开始处理任务
pool.execute(target); // 任务被拒绝
```

```java
// 使用 submit() 执行 Callable 任务，返回 Future 任务对象
Future<String> f1 = pool.submit(new MyCallable(10));

// Future 对象调用 get() 方法获取结果
try {
    String res1 = f1.get();
    System.out.println("res1 = " + res1);
} catch (Exception e) {
    e.printStackTrace();
}
```

#### Executors
使用 `Executors`（线程池工具类）调用方法返回不同特点的线程池对象；

| Executors 方法 | 说明 |
| --- | --- |
| public static ExecutorService newFixedThreadPool(int nThreads) | 创建固定线程数量的线程池，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程替代它 |
| public static ExecutorService newSingleThreadExecutor() | 创建只有一个线程的线程池对象，如果该线程出现异常而结束，那么线程池会补充一个新线程 |
| public static ExecutorService newCachedThreadPool() | 线程数量随着任务增加而增加，如果线程任务执行完毕且空闲了60s则会被回收掉 |
| public static ScheduledExecutorService newScheduledThreadPool(int corePoolsize) | 创建一个线程池，可以实现在给定的延迟后运行任务，或者定期执行任务 |




**注意事项：**

1. FixedThreadPool 和 singleThreadPool 允许的请求队列长度为 `Integer.MAX_VALUE`，可能会堆积大量请求，从而导致 OOM；
2. CachedThread 和 ScheduledThreadPool 允许的创建线程数量为`Integer.MAX_VALUE`，可能会堆积大量线程，从而导致 OOM
3. 大型并发系统中不建议使用 Executors 创建。

## 并发与并行
进程中的多个线程其实是并发和并行执行的。

### 并发
进程中的线程由 CPU 负责调度执行，但 CPU 能同时处理线程的数量有限，为了保证全部线程都能往前执行，**CPU 会轮询为系统的每个线程服务，这些任务并不一定是真正同时执行的**<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">，但</font>由于 CPU 切换的速度很快<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">（时间片轮转）</font>，让多个任务看起来像是同时运行，这就是并发。

### 并行
**在同一时刻，多个任务真正同时执行**，通常需要多核CPU或多处理器系统的支持。

## 线程的生命周期
Java 给线程定义了 6 种状态，都在 Thread 类的内部枚举类中

```java
    public enum State {
        // 新建
        NEW,

        // 可运行
        // 线程已经调用了 start()，等待 CPU 调度
        RUNNABLE,

        // 锁阻塞
        // 线程在执行的时候未竞争到锁对象，则该线程进入 Blocked 状态
        BLOCKED,

        // 无限等待
        // 一个线程进入 waiting 状态，另一个线程调用 notify() 才能唤醒
        WAITING,

        // 计时等待
        // 同 waiting 状态，有几个方法（sleep, wait）有超时参数，调用它们进入 Timed Waiting 状态
        TIMED_WAITING,

        // 被终止
        // 因为 run() 正常退出而死亡，或者因为没有捕获的异常终止了 run() 而死亡
        TERMINATED;
    }
```

# 二十三、网络编程
+ `java.net.*`包下提供了网络编程的解决方案。
+ 基本的通信架构有两种：CS架构（Client/Server）、BS架构（Browser/Server）

## IP 地址
InetAddress 代表 IP 地址

| InetAddress 常用方法 | 说明 |
| --- | --- |
| public static InetAddress getLocalHost() | 获取本机IP，会以一个 inetAddress 的对象返回 |
| public static InetAddress getByName(String host) | 根据ip地址或者域名，返回一个inetAdress对象 |
| public string getHostName() | 获取该ip地址对象对应的主机名 |
| public String getHostAddress() | 获取该ip地址对象中的ip地址信息 |
| public boolean isReachable(int timeout) | 在指定毫秒内，判断主机与该ip对应的主机是否能连通 |


```java
// 获取本机 IP 地址对象
InetAddress addr1 = InetAddress.getLocalHost();
System.out.println("ip地址：" + addr1.getHostAddress());
System.out.println("主机名：" + addr1.getHostName());

// 获取对方 IP 地址对象
InetAddress addr2 = InetAddress.getByName("www.baidu.com");
System.out.println("ip地址：" + addr2.getHostAddress());
System.out.println("主机名：" + addr2.getHostName());

// 判断本机与主机是否联通（相当于 ping）
System.out.println(addr2.isReachable(5000));
```

## 端口
标记正在计算机设备上运行的应用程序，被规定为一个 16 位的二进制，范围是 0~65535。

周知端口：0~1023，被预先定义的知名应用占用（HTTP 占用 80，FTP 占用 21）。

**注册端口**：1024~49151，分配给用户进程或某些应用程序。

动态端口：49151~65535，一般不固定分配某种进程，而是动态分配。

自己开发的程序一般选择使用注册端口，且一个设备中不能出现两个程序的端口号一样，否则除错。

## UDP 通信
### DatagramSocket
用于创建客户端、服务端

| 构造器 | 说明 |
| --- | --- |
| public DatagramSocket() | 创建<font style="color:#DF2A3F;">客户端</font>的socket对象，系统会随机分配一个端口号 |
| public DatagramSocket(int port) | 创建<font style="color:#DF2A3F;">服务端</font>的socket对象，并指定端口号 |


| 方法 | 说明 |
| --- | --- |
| public void send(DatagramPacket dp) | 发送数据包 |
| public void receive(DatagramPacket p) | 使用数据包接收数据 |


### DatagramPacket
创建数据包，封装要发送的数据。

| 构造器 | 说明 |
| --- | --- |
| public DatagramPacket(byte[] buf, int length, InetAddress address，int port) | 创建发出去的数据包对象 |
| public DatagramPacket(byte[]buf,int length) | 创建用来接收数据的数据包 |


| 方法 | 说明 |
| --- | --- |
| public int getLength() | <font style="color:rgb(71, 71, 71);">返回要发送的数据的长度或接收的数据的长度</font> |
| public InetAddress getAddress() | <font style="color:rgb(71, 71, 71);">返回发送此数据报或从中接收数据报的计算机的IP地址</font> |
| public int getPort() | <font style="color:rgb(71, 71, 71);">返回发送此数据报或从中接收数据报的远程主机上的端口号</font> |


```java
package com.udp.create;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) throws IOException {
        // 1. 创建客户端发送对象
        DatagramSocket socket = new DatagramSocket();

        // 2. 创建数据包对象，封装要发送的数据
        byte[] buf = "客户端发送的数据".getBytes();
        DatagramPacket packet = new DatagramPacket(
                buf, // 数据
                buf.length, // 数据长度
                InetAddress.getLocalHost(), // 对方 IP
                8888 // 对方端口
        );

        // 3. 发送数据
        socket.send(packet);

        // 4. 释放资源
        socket.close();
        System.out.println("客户端发送完毕");
    }
}
```

```java
package com.udp.create;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) throws IOException {
        // 1. 创建服务端接收对象
        DatagramSocket socket = new DatagramSocket(8888);

        // 2. 创建数据包对象，作为接收数据的容器
        byte[] buf = new byte[1024 * 64];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        // 3. 接收数据
        socket.receive(packet);

        // 4. 获取数据
        int len = packet.getLength();
        String msg = new String(buf, 0, len);
        System.out.println("后端接收到的消息是：" + msg);

        // 获取发送端信息
        InetAddress ip = packet.getAddress();
        System.out.println("客户端ip：" + ip.getHostAddress());
        System.out.println("客户端端口：" + packet.getPort());

        // 5. 释放资源
        socket.close();
    }
}

```

### 客户端多开
![](https://cdn.nlark.com/yuque/0/2025/png/29092218/1746166931547-a60c5edf-e5a1-41f6-93ff-e72a1700dc53.png)

## TCP 通信
### Socket 客户端
| 构造器 | 说明 |
| --- | --- |
| public Socket(String host, int port) | 根据指定的服务器 IP、端口号请求与服务端建立连接，连接通过就获得了客户端的 socket |


| 方法 | 说明 |
| --- | --- |
| public OutputStream getOutputStream() | 获取字节输出流对象 |
| public ItputStream getIntputStream() | 获取字节输入流对象 |


```java
package com.net.tcp;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        // 1. 创建 socket 对象，与服务端进行连接
        Socket client = new Socket(
                InetAddress.getLocalHost().getHostAddress(),
                8888
        );

        // 2. 从 socket 通信管道中获取字节输出流，用来发数据给服务端
        OutputStream os = client.getOutputStream();

        // 3. 把低级的字节输出流包装成数据输出流
        DataOutputStream dos = new DataOutputStream(os);

        // 4. 写数据出去（支持多发）
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入信息：");
            String msg = sc.nextLine();

            // 退出逻辑
            if (msg.equals("exit")) {
                dos.close();
                client.close();
                System.out.println("退出成功，欢迎下次使用");
                break;
            }

            dos.writeUTF(msg);
            dos.flush();
        }
    }
}
```

### ServerSocket 服务端
| 构造器 | 说明 |
| --- | --- |
| public ServerSocket(int port) | 为服务端程序注册端口 |


| 方法 | 说明 |
| --- | --- |
| public Socket accept() | 阻塞等待客户端的连接请求，一旦与某个客户端成功连接，则返回服务端这边的 Socket 对象。 |


```java
package com.net.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        // 1. 创建 ServerSocket 服务端对象
        ServerSocket server = new ServerSocket(8888);

        // 2. 调用 accept() 方法，等待客户端的连接请求
        Socket client = server.accept();

        // 3. 从客户端管道中得到一个字节输入流，并使用数据输入流包装
        DataInputStream dis = new DataInputStream(client.getInputStream());

        // 4. 使用数据输入流读取客户端发送过来的消息（支持多收）
        while (true) {
            try {
                String data = dis.readUTF();
                System.out.println("服务端接受到的数据：" + data);
            } catch (IOException e) {
                System.out.println(client.getRemoteSocketAddress() + "已离线！"); // 获取客户端的地址
                dis.close();
                server.close();
                System.out.println("服务关闭。");
                break;
            }
        }
    }
}

```

### 多线程多端通信
解决方案：主线程负责接受客户端连接，子线程负责与客户端通信。

[在线Demo](https://github.com/Zhangyao719/java-study/tree/main/net-module/src/com/net/tcp)

### 群聊案例
1. 端口转发思想；
2. 服务端存储在线 Socket（集合）；

[在线Demo](https://github.com/Zhangyao719/java-study/tree/main/net-module/src/com/net/wechat)

### 简易版BS架构
1. 没有客户端；
2. 响应浏览器，遵循 HTTP 协议格式，提供内容；
3. 线程池优化

[在线Demo](https://github.com/Zhangyao719/java-study/tree/main/net-module/src/com/net/bs)

# 二十四、Junit 单元测试
## 使用步骤
1. 将 Junit 框架的 jar 包导入到项目中（IDEA已集成）；
2. 为需要测试的业务类，定义对应的测试类，并为每个业务方法，编写对应的测试方法(必须：公共、无参、无返回值)；
3. <font style="color:#DF2A3F;">测试方法上必须声明</font> `@Test` <font style="color:#DF2A3F;">注解</font>，然后在测试方法中，编写代码调用被测试的业务方法进行测试；
4. 使用 `Assert.assertEquals`断言，可以指定想要的测试结果，如果当前结果不符合预期，会提示错误；
5. 如果右键没有 Run 按钮，请在 `Project Structure`→ `Modules`→ 选择 `Sources`→ 将 `src`文件夹标记为（`Marks as`）`Tests`；
6. 开始测试：选中测试方法，右键选择 `Run 'xxx()'`，如果<font style="color:#74B602;">测试通过则是绿色</font>；如果<font style="color:#DF2A3F;">测试失败，则是红色</font>；
7. 全局测试：右击左侧边栏整个项目顶层目录，执行 `Run 'All Tests'`。

```java
import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {
    @Test
    public void testUtil() {
        int res = Utils.xxx();

        // 测试断言
        Assert.assertEquals(
            // 参数一：测试失败是的提示
            "测试失败",
            // 参数二：测试期望值
            -1,
            // 参数三：当前测试结果
            res
        );
    }
}
```

## 常用注解
| Junit 4 注解 | Junit 5 注解 | 说明 |
| --- | --- | --- |
| @Test | @Test | 测试类中的方法必须用它修饰才能成为测试方法，才能启动执行 |
| @Before | @BeforeEach | 用来修饰一个实例方法，该方法会在每一个测试方法执行之前执行一次。 |
| @After | @AfterEach | 用来修饰一个实例方法，该方法会在每一个测试方法执行之后执行一次。 |
| @BeforeClass | @BeforeAll | 用来修饰一个静态方法，该方法会在所有测试方法之前只执行一次。 |
| @AfterClass | @AfterAll | 用来修饰一个静态方法，该方法会在所有测试方法之后只执行一次。 |


+ 在测试方法执行前执行的方法，常用于：初始化资源；
+ 在测试方法执行完后再执行的方法，常用于：释放资源。

# 二十五、反射
反射就是加载某个类，并允许以编程的方式解剖类中的各种成分（成员变量、方法、构造器等）。

+ 加载类，获取类的字节码：`Class`对象；
+ 获取类的构造器：`Constructor`对象；
+ 获取类的成员变量：`Field`对象；
+ 获取类的成员方法：`Method`对象

## 获取 Class 对象
三种获取方式：

1. Class c1 = 类名.class;
2. 调用 Class 提供的方法：public static Class forName(String package);
3. Object 提供的方法：public Class getClass(); class c3 = 对象.getClass();

```java
// 方式一：类名.class
Class c1 = Student.class;

// 方式二：Class.forName(全类名)
Class c2 = Class.forName("com.reflect.getClass.Student");

// 方式二：对象.getClass()
Student s1 = new Student();
Class c3 = s1.getClass();
```

## 获取类的构造器
| Class 提供了从类中获取构造器的方法 | 说明 |
| --- | --- |
| Constructor<?>[]getConstructors() | 获取全部构造器(只能获取 public 修饰的) |
| Constructor<?>[]getDeclaredConstructors() | 获取全部构造器(只要存在就能拿到) |
| Constructor<T>getConstructor(Class<?>... parameterTypes) | 获取某个构造器(只能获取 public 修饰的) |
| Constructor<T>getDeclaredConstructor(Class<?>... parameterTypes) | 获取某个构造器(只要存在就能拿到) |


```java
// 1. 获取全部构造器
Constructor[] constructors = c1.getDeclaredConstructors();
for (Constructor con : constructors) {
    System.out.println("构造器：" + con.getName() + "，参数个数：" + con.getParameterCount());
}

// 2. 获取某个构造器
// 2.1 获取无参构造器
Constructor constructor1 = c1.getDeclaredConstructor();
// 2.2 获取指定参数类型的构造器
Constructor constructor2 = c1.getDeclaredConstructor(String.class, int.class, double.class);
```

### 初始化实例对象
| Constructor 提供的方法 | 说明 |
| --- | --- |
| T newInstance(Object... initargs) | 调用此构造器对象表示的构造器，并传入参数，完成对象的初始化并返回 |
| public void setAccessible(boolean flag) | 设置为 true，表示禁止检查访问控制（暴力反射） |


```java
// 使用构造器创建实例对象（私有的构造器不能创建对象）
Student s2 = (Student) constructor1.newInstance();
Student s3 = (Student) constructor2.newInstance("张三", 18, 99);
```

### 暴力反射
如果非要使用私有构造器，可以调用 `setAccessible(true)`禁止检查访问权限

```java
Class c1 = Student.class;

// 1. 获取私有构造器
Constructor privateCtor = c1.getDeclaredConstructor(String.class, int.class);

// 2. 暴力反射，强行使用
privateCtor.setAccessible(true);
Student s3 = (Student) privateCtor.newInstance("张三", 18);
```

## 获取类的成员变量
| Class 提供了从类中获取成员变量的方法 | 说明 |
| --- | --- |
| public Field[] getFields() | 获取类的全部成员变量（只能获取 public 修饰的） |
| public Field[] getDeclaredFields() | 获取类的全部成员变量（只要存在就能拿到） |
| public Field getField(string name) | 获取类的某个成员变量（只能获取 public 修饰的） |
| public Field getDeclaredField(string name) | 获取类的某个成员变量（只要存在就能拿到） |


### 取值与赋值
| Field 提供的实例方法 | 说明 |
| --- | --- |
| void set(Object obj, Object value) | 复制 |
| Object get(Object obj) | 取值 |
| public void setAccessible(boolean flag) | true 表示禁止检查访问控制（暴力反射） |


```java
Class c1 = Student.class;

// 获取全部成员变量
Field[] fields = c1.getDeclaredFields();
for (Field f : fields) {
    System.out.println("成员变量的类型：" + f.getType() + "，成员变量的名字" + f.getName());
}

// 定位某个成员变量
Field nameField = c1.getDeclaredField("name");

Student student = new Student();
nameField.setAccessible(true);
nameField.set(student, "张三");

String name = (String) nameField.get(student);
System.out.println(name);
```

## 获取类的成员方法
| Class 提供了从类中获取成员方法的方法 | 说明 |
| --- | --- |
| public Method[] getMethods() | 获取类的全部成员方法（只能获取 public 修饰的） |
| public Method[] getDeclaredMethods() | 获取类的全部成员方法（只要存在就能拿到） |
| Method getMethod(String name, class<?>... parameterTypes) | 获取类的某个成员方法（只能获取 public 修饰的） |
| Method getDeclaredMethod(String name,class<?>... parameterTypes) | 获取类的某个成员方法(只要存在就能拿到) |


### 执行调用
| Method 提供的实例方法 | 说明 |
| --- | --- |
| public Object invoke(Object obj, Object... args) | 触发某个对象的该方法执行 |
| public void setAccessible(boolean flag) | true 表示禁止检查访问控制（暴力反射） |


```java
Class c1 = Student.class;

// 获取全部方法
Method[] methods = c1.getDeclaredMethods();
for (Method m : methods) {
    System.out.println("成员方法的名字：" + m.getName() + "，参数个数：" + m.getParameterCount());
}

// 获取单个方法（可以通过参数类型区分重载）
Method eatMethod1 = c1.getDeclaredMethod("eat");
Method eatMethod2 = c1.getDeclaredMethod("eat", String.class);

// invoke 调用实例方法
Student student = new Student("张三", 18, 99.9);
Object result = eatMethod1.invoke(student);
System.out.println(result);

// 传递参数
eatMethod2.invoke(student, "薯片");

// 暴力反射
Method eatMethod3 = c1.getDeclaredMethod("play", String.class);
eatMethod3.setAccessible(true);
eatMethod3.invoke(student, "唱跳rapper");
```

## 作用、应用场景
+ 得到一个类的全部成分，然后操作；
+ 可以破坏封装性；
+ 适合做 Java 的框架。基本上，主流的框架都会基于反射设计出一些通用功能；

# 二十六、注解
注解（Annotation）是 Java 代码里的特殊标记，比如 @Test、@Override，作用是让其他程序根据注解信息决定怎么执行该程序。

注解可以用在类、构造器、方法、成员变量、参数等位置上。

## 自定义注解
> public @interface 注解名称 {
>
> public 属性类型 属性名() default 默认值;
>
> }
>

### 特殊属性名 value
如果注解中只有一个 value 属性，使用注解时，value 名称可以不写。

### 注解原理
+ 本质上是一个接口，继承了`Annotation`接口，里面的属性其实都是抽象方法；
+ @注解(...) 其实就是一个匿名类对象，实现了该注解以及 Annotation 接口；

## 元注解
元注解(Meta-annotations)是用于注解其他注解的注解，在Java中，元注解定义在`java.lang.annotation` 包中。它们控制着注解的行为和使用方式。

### <font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">@Target</font>
<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">声明被修饰的注解只能在哪些位置使用，指定注解可以应用的目标元素类型。</font>

> @Target(Element.Type)
>
> public @interface Xxx {
>
> }
>

| Element 枚举 | 说明 |
| --- | --- |
| TYPE | 类、接口 |
| FIELD | 成员变量 |
| METHOD | 成员方法 |
| PARAMETER | 方法参数 |
| CONSTRCTOR | 构造器 |
| LOCAL_VARIABLE | 局部变量 |


### @Retention
指定注解的保留策略，即注解在何时有效。

| RetentionPolicy 枚举 | 说明 |
| --- | --- |
| SOURCE | 只作用在源码阶段，字节码文件中不存在 |
| CLASS | 保留到字节码文件阶段，运行阶段不存在，默认值 |
| RUNTIME | 一直保留到运行阶段，开发常用 |


## 注解的解析
判断类、方法、成员变量上是否存在注解，并把注解里的内容解析出来。

| AnnotatedElement 接口扎供了解析注解的方法 | 说明 |
| --- | --- |
| public Annotation[] getDeclaredAnnotations() | 获取当前对象上面的注解 |
| public T getDeclaredAnnotation(Class<T> annotationClass) | 获取指定的注解对象 |
| public boolean isAnnotationPresent(class<Annotation> annotationclass) | 判断当前对象上是否存在某个注解 |


```java
// 注解：
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTest {
    String value();
    double aaa() default 9.9;
    String[] bbb();
}

// 使用注解修饰类和方法：
@MyTest(value = "类上的注解", bbb = {"A", "B", "C"})
class Demo {
    @MyTest(value = "方法上的注解", aaa = 88, bbb = {"a", "b", "c"})
    public void handle() {}
}

// 获取注解信息
public class DemoTest {
    @Test
    public void run() throws Exception {
        Class demo = Demo.class;
        // 判断当前类上是否陈列该注解
        if (demo.isAnnotationPresent(MyTest.class)) {
            // 得到注解对象
            MyTest annotation = (MyTest) demo.getDeclaredAnnotation(MyTest.class);
            System.out.println(annotation.value());
            System.out.println(annotation.aaa());
            System.out.println(Arrays.toString(annotation.bbb()));
        }
    
        // 判断方法上是否陈列该注解
        Method method = demo.getDeclaredMethod("handle");
        if (method.isAnnotationPresent(MyTest.class)) {
            // 得到注解对象
            MyTest annotation = (MyTest) method.getDeclaredAnnotation(MyTest.class);
            System.out.println(annotation.value());
            System.out.println(annotation.aaa());
            System.out.println(Arrays.toString(annotation.bbb()));
        }
    }
}
```

# 二十七、动态代理
<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">动态代理是一种在运行时动态生成代理类的机制，它允许你</font>**<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">在不修改原始类代码的情况下，为方法调用添加额外的行为</font>**<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">（如日志记录、事务管理、权限检查等）。动态代理主要通过 </font>`java.lang.reflect.Proxy`<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);"> 和 </font>`java.lang.reflect.InvocationHandler`来实现。

## 接口代理
| Proxy 创建代理的方法 | 说明 |
| --- | --- |
| newProxyInstance(ClassLoader loader, 类<?>[] interfaces, InvocationHandler h) | <font style="color:rgb(71, 71, 71);">返回指定接口的代理实例，该接口将方法调用分派给指定的调用处理程序。</font> |


| newProxyInstance 的三个参数 | 说明 |
| --- | --- |
| ClassLoader loader | <font style="color:rgb(53, 56, 51);">用于定义代理类的类加载器</font> |
| 类<?>[] interfaces | <font style="color:rgb(53, 56, 51);">要实现的代理类的接口列表（有哪些方法）</font> |
| InvocationHandler h | <font style="color:rgb(53, 56, 51);">调度方法调用的调用处理程序（代理对象要干什么事）</font> |


| InvocationHandler 的 invoke() 三个形参 | 说明 |
| --- | --- |
| Object proxy | 当前的代理对象 |
| Method method | 当前代理对象调用的方法 |
| Object[] args | 调用方法时传递的参数 |


### 定义接口
```java
public interface Star {
    String sing(String song);
    void dance();
}
```

### 实现接口
```java
@AllArgsConstructor
public class RoleStar implements Star {
    private String name;

    public String sing(String song) {
        System.out.println(name + "唱了一首" + song);
        return name + "说：哎哟，不错哦";
    }

    public void dance() {
        System.out.println(name + "跳了一支天鹅湖。");
    }
}
```

### 封装代理方法，创建 InvocationHandler 实现
```java
public class ProxyUtils {
    /**
     * 创建经纪人
     *
     * @param roleStar 明星
     * @return 满足明星方法的经纪人
     */
    public static Star createBroker(RoleStar roleStar) {
        Star broker = (Star) Proxy.newProxyInstance(
                // 参数一：ClassLoader 指定类加载器，加载生成的代理类
                ProxyUtils.class.getClassLoader(),
            
                // 参数二：指定生成的代理长什么样子（有哪些方法，是一个数组）
                new Class[]{Star.class},
            
                // 参数三：指定生成的代理对象要什么事情（代理调用方法，方法会执行 invoke()）
                new InvocationHandler() {
                    @Override
                    public Object invoke(
                            Object proxy, // 经纪人对象
                            Method method, // 经纪人调用的方法
                            Object[] args // 经纪人调用方法时传递的参数
                    ) throws Throwable {
                        // 代理对象要做的事情，会写在这里
                        if (method.getName().equals("sing")) {
                            System.out.println("经纪人正在准备演唱会");
                        } else if (method.getName().equals("dance")) {
                            System.out.println("经纪人正在准备大剧院");
                        } else {
                            System.out.println("无需经纪人");
                        }

                        // 最终会调用 roleStar 上对应的方法，并接受返回值
                        return method.invoke(roleStar, args);
                    }
                }
        );
        return broker;
    }
}
```

### <font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">创建动态代理实例</font>
```java
// 1. 创建明星
RoleStar jayZhou = new RoleStar("周杰伦");
// 2. 创建明星的经纪人
Star broker = ProxyUtils.createBroker(jayZhou);

String data = broker.sing("《晴天》"); // 调用代理方法，会执行 invoke() 方法，最终会调用明星的方法
System.out.println(data);

broker.dance();
```

**注意事项：**

+ 对象和代理都实现同一个接口，所以他们有相同的方法；

