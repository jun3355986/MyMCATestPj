package com.jun.arithmetic;

/**
 * @decription: 链表操作
 * @date: 2022/4/26 16:12
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class LinkedListMid {

    public static class Node{
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }

    }

    /**
     * 奇数个返回中间，偶数个返回上中
     * @param head
     * @return
     */
    public static Node midOrUpMidNode(Node head) {

        Node p1 = head;
        Node p2 = head;

        while (p1 != null && p1.next != null && p1.next.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
        }
        return p2;

    }

    public static Node midOrDownMidNode(Node head) {

        Node p1 = head;
        Node p2 = head;

        while (p1 != null && p1.next != null && p1.next.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
        }
        return p2;

    }



}
