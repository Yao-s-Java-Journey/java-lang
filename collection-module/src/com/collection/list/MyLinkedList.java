package com.collection.list;

import java.util.StringJoiner;

public class MyLinkedList<E> {
    private int size = 0;

    MyLinkedList.Node first; // 头指针

    // 定义内部节点类，用于创建节点对象，封装节点数据和下个节点对象的地址
    public static class Node<E> {
        E item; // 当前节点
        Node<E> next; // 下个节点

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    public boolean add(E e) {
        // 维护单链表
        // 第一个节点或最后一个节点
        // 创建节点对象，封装这个数据
        Node<E> newNode = new Node<>(e, null);

        // 判断这个节点是否是第一个节点
        if (first == null) {
            first = newNode;
        } else {
            // 把这个节点加入到当前最后一个节点的下一个位置
            // 如何找到最后一个节点对象
            Node<E> temp = first;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        Node<E> temp = first;
        while (temp != null) {
            sj.add(temp.item + "");
            temp = temp.next;
        }
        return sj.toString();
    }

    public int size() {
        return size;
    }
}

class Demo  {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println("list = " + list);
    }
}