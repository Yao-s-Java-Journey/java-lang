package com.io.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;

public class ObjectOutputStreamTest {
    public static void main(String[] args) {
        writeStudent();
        readStudent();
    }

    public static void writeStudent() {
        Student student = new Student("张三", 18, 1.72, 123456);
        try (
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream("io-module\\src\\com\\io\\test\\data6.txt")
                );
        ) {
            // 序列化，将对象写出去
            oos.writeObject(student);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void readStudent() {
        try (
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream("io-module\\src\\com\\io\\test\\data6.txt")
                );
        ) {
            // 反序列化，从文件中读出数据
            // Object data = ois.readObject();
            Student data = (Student) ois.readObject();
            System.out.println("data = " + data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Student implements Serializable {
    private String name;
    private int age;
    private Double height;
    private transient int password; // 不参与序列化
}
