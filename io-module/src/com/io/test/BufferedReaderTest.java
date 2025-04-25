package com.io.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class BufferedReaderTest {
    public static void main(String[] args) {
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
    }
}
