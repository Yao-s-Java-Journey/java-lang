package com.collection.list;

import java.util.LinkedList;

public class linkedList {
    public static void main(String[] args) {
        LinkedList<String> queue1 = new LinkedList<String>();
        // 按队列添加元素（先进）
        queue1.addLast("第一个人");
        queue1.addLast("第二个人");
        queue1.addLast("第三个人");
        // 按队列移除元素（先出）
        queue1.removeFirst();
        queue1.removeFirst();
        System.out.println("queue = " + queue1);

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
        
    }
}
