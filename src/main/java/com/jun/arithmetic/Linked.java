package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

/**
 * @decription: 链表
 * @date: 2022/4/2 14:03
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class Linked {

    public static class Node {
        private Node next;
        public String d;

        public Node() {}
        public Node(String d) {
            next = null;
            this.d = d;
        }

        public Node(String d, Node node) {
            next = node;
            this.d = d;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext(Node next) {
            return this.next ;
        }
    }

    public static class DoubleNode {
        private DoubleNode last;
        private DoubleNode next;
        public String d;

        public DoubleNode() {}
        public DoubleNode(String d) {
            this.next = null;
            this.last = null;
            this.d = d;
        }


        public DoubleNode(String d, DoubleNode last, DoubleNode next) {
            this.next = next;
            this.last = last;
            this.d = d;
        }

        public void setNext(DoubleNode next) {
            this.next = next;
        }
        public void setLast(DoubleNode last) {
            this.last = last;
        }

        public DoubleNode getNext() {
            return this.next;
        }
        public DoubleNode getLast() {
            return this.last;
        }
    }

    /**
     * a(h) -> b ->c -> null
     * c(h) -> b -> a -> null
     * 反转链单表
     */
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while(head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 反转双向链表
     * a(h) -> b ->c -> null
     * c(h) -> b -> a -> null
     * @return
     */
    public static DoubleNode reverseDoubleLinkedList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void printLinked(Node head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.d).append("->");
            head = head.next;
        }
        sb.append("null");
        log.info("链表结构：{}", sb);

    }

    /**
     * 删除链表指定的值
     * @param head
     */
    public static Node removedNode(Node head, String s) {
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            if (cur.d.equals(s)) {
                // head 的值相等
                if (pre == null) {
                    head = cur.next;
                } else {
                    pre.next = cur.next;
                }
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }


}
