package com.jun.arithmetic;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * @decription: 复制随机链表问题
 * @date: 2022/5/7 9:27
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class CopyListWithRandom {

    public static class Node {
        int val;
        Node next;
        Node random;

        public int getVal() {
            return val;
        }

        public String getNext() {
            return next == null ? "null" : next.val + "";

        }

        public String getRandom() {
            return random == null ? "null" : random.val + "";
        }

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // 使用额外容器实现 hashMap实现
    public static Node copyRandomList1(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            /**
             * 【出错了】这里指向了旧的连表
             */
//            map.get(cur).next = cur.next;
//            map.get(cur).random = cur.random;
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);

    }

    // 每copy节点插入旧节点中间
    public static Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node oldNext = null;
        while (cur != null) {
            oldNext = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = oldNext;
            cur = oldNext;
        }

        cur = head;
        Node newHead = cur.next;
        Node newNode = newHead;
        oldNext = newNode.next;
        newNode.random = cur.random == null ? null : cur.random.next;
        while ( newNode.next != null ) {
//            cur = oldNext;
//            oldNext = cur.next.next;
//            newNode = cur.next;
//            newNode.next = cur.next.next == null ? null : cur.next.next.next;
            /**
             * 【这里出错了】在指向前面的元素时，由于修正了原来连接的连接，所以oldNext指向了原来旧的正确的next，而不是新的那个next，newNode.random就会指错到了旧链表上去了
             * 因此，第二个循环不能修正先，即不能cur.next = oldNext
             */
//            newNode.random = cur.random == null ? null : cur.random.next;
//            cur.next = oldNext;
            cur = oldNext;
            newNode = cur.next;
            oldNext = newNode.next;
            newNode.random = cur.random == null ? null : cur.random.next;
        }

        cur = head;
        newNode = cur.next;
        oldNext = newNode.next;
        newNode.next = oldNext == null ? null : oldNext.next;
        cur.next = oldNext;
        while (newNode.next != null) {
            cur = oldNext;
            newNode = cur.next;
            oldNext = newNode.next;
            newNode.next = oldNext == null ? null : oldNext.next;
            cur.next = oldNext;
        }

        return newHead;
    }

    // 老师的写法
    public static Node copyRandomList3(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node oldNext = null;
        while (cur != null) {
            oldNext = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = oldNext;
            cur = oldNext;
        }
        cur = head;
        Node copy = null;
        while (cur != null) {
            oldNext = cur.next.next;
            copy = cur.next;
            copy.random = cur.random == null ? null : cur.random.next;
            cur = oldNext;
        }
        Node res = head.next;
        cur = head;
        while (cur != null) {
            oldNext = cur.next.next;
            copy = cur.next;
            copy.next = oldNext == null ? null : oldNext.next;
            cur.next = oldNext;
            cur = oldNext;
        }
        return res;
    }


    public static void main(String[] args) {
        Node node = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node.next = node2;
        node.random = node5;
        node2.next = node3;
        node2.random = node7;
        node3.next = node4;
        node3.random = node4;
        node4.next = node5;
        node4.random = null;
        node5.next = node6;
        node5.random = node3;
        node6.next = node7;
        node6.random = node2;
        node7.next = null;
        node7.random = node;

        log.info("期望：");
        printLink(node);

//        log.info("结果1：");
//        printLink(copyRandomList1(node));
//        printLink(node);

        log.info("结果2：");
        printLink(copyRandomList2(node));
        printLink(node);


    }

    public static void printLink(Node head) {
        if (head == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(JSON.toJSONString(head)).append("->");
            head = head.next;
        }
        log.info("result: {}", sb);
    }

}
