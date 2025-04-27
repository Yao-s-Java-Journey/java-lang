package com.io.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DataOutputStreamTest {
    public static void main(String[] args) {
        writeData();
        readData();
    }

    public static void writeData() {
        try (
                DataOutputStream dos = new DataOutputStream(new FileOutputStream("io-module\\src\\com\\io\\test\\data5.txt"));
        ) {
            // 写数据和类型出去
            dos.writeByte(97);
            dos.writeBoolean(false);
            dos.writeChar('a');
            dos.writeUTF("哈哈哈");
            dos.writeInt(999);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void readData() {
        try (
                DataInputStream dos = new DataInputStream(new FileInputStream("io-module\\src\\com\\io\\test\\data5.txt"));
        ) {
            // 读取
            byte b1 = dos.readByte();
            System.out.println("b1 = " + b1);

            boolean b2 = dos.readBoolean();
            System.out.println("b2 = " + b2);

            char c = dos.readChar();
            System.out.println("c = " + c);

            String s = dos.readUTF();
            System.out.println("s = " + s);

            int i = dos.readInt();
            System.out.println("i = " + i);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
