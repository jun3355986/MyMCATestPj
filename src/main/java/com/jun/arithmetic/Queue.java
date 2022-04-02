package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

/**
 * @decription: 队列
 * @date: 2022/4/2 16:59
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class Queue {

    // 实现队列功能：先进先出，加入（头部/尾部）数据，弹出数据（头部/尾部）
    public Linked.DoubleNode head = null;
    public Linked.DoubleNode tail = null;

    // 双向链表实现队列----------------------------------------------------
    // 加入数据：从头部
    public void addFromHead(String d) {
        Linked.DoubleNode node = new Linked.DoubleNode(d);
        if (head != null) {
            node.setNext(head);
            head.setLast(node);
            head = node;
        } else {
            head = node;
            tail = node;
        }
    }
    // 加入数据：从尾部
    public void addFromBottom(String d) {
        Linked.DoubleNode node = new Linked.DoubleNode(d);
        if (tail != null) {
            node.setLast(tail);
            tail.setNext(node);
            tail = node;
        } else {
            head = node;
            tail = node;
        }
    }


    // 弹出数据：从头部
    public Linked.DoubleNode popFromTop() {
        if (head == null) {
            return null;
        }
        Linked.DoubleNode popNode = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setLast(null);
            popNode.setNext(null);
        }
        return popNode;
    }

    // 弹出数据：从尾部
    public Linked.DoubleNode popFromBottom() {
        if (tail == null) {
            return null;
        }
        Linked.DoubleNode popNode = tail;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.getLast();
            tail.setNext(null);
            popNode.setLast(null);
        }
        return popNode;
    }

    // 数组实现队列----------------------------------------------------
    public static void printLinked(Queue queue) {
        StringBuilder sb = new StringBuilder();
        Linked.DoubleNode tail = queue.tail;
        while (tail != null) {
            sb.append(tail.d).append(",");
            tail = tail.getLast();
        }
        log.info("队列内容：{}", sb);

    }

}
