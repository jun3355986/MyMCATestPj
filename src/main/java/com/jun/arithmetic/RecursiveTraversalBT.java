package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

/**
 * @decription: 二叉树的递归
 * @date: 2022/5/13 10:14
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class RecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) { this.value = value;}
    }

    // temple
    public static void f (Node head) {
        if (head == null) {
            return;
        }
        // 1
        f(head.left);
        // 2
        f(head.right);
        // 3
    }

    // 先序 头 左 右
    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        log.info("{}", head.value);
        pre(head.left);
        pre(head.right);
    }

    // 中序 左 头 右
    public static void in(Node head) {
        if (head == null) {
            return;
        }
        in(head.left);
        log.info("{}", head.value);
        in(head.right);
    }

    // 后序 左 右 头
    public static void pos(Node head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        log.info("{}", head.value);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos(head);
        System.out.println("========");

    }

}
