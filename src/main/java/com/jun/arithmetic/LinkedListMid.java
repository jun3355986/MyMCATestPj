package com.jun.arithmetic;

import java.util.ArrayList;

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

    /**
     * 奇数个返回中间，偶数个返回下中
     * @param head
     * @return
     */
    public static Node midOrDownMidNode(Node head) {

        Node p1 = head;
        Node p2 = head;

        while (p1 != null && p1.next != null ) {
            p1 = p1.next.next;
            p2 = p2.next;
        }
        return p2;

    }

    /**
     * 奇数返回中上，偶数长度都返回中后的
     * @param head
     * @return
     */
    public static Node midOrUpMidPreNode(Node head) {
        Node p1 = head;
        Node p2 = head;

        if (p1 == null || p1.next == null || p1.next.next == null) {
            return p2;
        }
        p1 = p1.next.next;
        while (p1 != null && p1.next != null && p1.next.next != null){
            p1 = p1.next.next;
            p2 = p2.next;
        }
        return p2;
    }

    /**
     * 奇数或偶数长度都返回中前的数 奇数返回中点前一个，偶数返回下中点前一个
     * @param head
     * @return
     */
    public static Node midOrDownMidPreNode(Node head) {
        Node p1 = head;
        Node p2 = head;

        if (p1 == null || p1.next == null) {
            return p2;
        }
        p1 = p1.next.next;
        while (p1 != null && p1.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
        }
        return p2;
    }



    public static Node right1(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 1) / 2);
    }

    public static Node right2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get(arr.size() / 2);
    }

    public static Node right3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 3) / 2);
    }

    public static Node right4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 2) / 2);
    }

    public static void main(String[] args) {
        Node test = null;
        test = new Node(0);
        test.next = new Node(1);
        test.next.next = new Node(2);
        test.next.next.next = new Node(3);
        test.next.next.next.next = new Node(4);
        test.next.next.next.next.next = new Node(5);
        test.next.next.next.next.next.next = new Node(6);
        test.next.next.next.next.next.next.next = new Node(7);
        test.next.next.next.next.next.next.next.next = new Node(8);

        Node ans1 = null;
        Node ans2 = null;

        ans1 = midOrUpMidNode(test);
        ans2 = right1(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");
        System.out.println("------------------------------");

        ans1 = midOrDownMidNode(test);
        ans2 = right2(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");
        System.out.println("------------------------------");


        ans1 = midOrUpMidPreNode(test);
        ans2 = right3(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");
        System.out.println("------------------------------");


        ans1 = midOrDownMidPreNode(test);
        ans2 = right4(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");
        System.out.println("------------------------------");


    }





}
