package com.xml.demo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws DocumentException {
        // 需求：解析 data.xml 文件信息
        // 1. 创建解析器对象
        SAXReader saxReader = new SAXReader();

        // 2. 把 XML 文件读成一个 Document 文档对象
        Document document = saxReader.read("property-module\\data.xml");

        // 3. 文档对象中包含了 XML 的全部数据，提供了获取数据的方法
        Element rootElement = document.getRootElement();

        List<Student> students = new ArrayList<>();

        List<Element> sonEles = rootElement.elements("contact");
        sonEles.forEach(element -> {
            Student student = new Student();
            student.setId(Integer.parseInt(element.attributeValue("id")));
            student.setName(element.elementTextTrim("name"));
            student.setEmail(element.elementTextTrim("email"));
            student.setGender(element.elementTextTrim("gender").charAt(0));
            students.add(student);
        });

        System.out.println(students);
    }
}
