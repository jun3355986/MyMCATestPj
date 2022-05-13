package com.jun.arithmetic;

import java.util.Stack;

/**
 * @decription: 非递归方式遍历二叉树
 * @date: 2022/5/13 10:46
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class UnRecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        stack.push(cur);
        while(!stack.empty()) {
            cur = stack.pop();
            System.out.print(cur.value+ " ");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        System.out.println();
    }

    public static void in(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (!stack.empty() || cur != null ) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
        System.out.println();
    }

    public static void pos1(Node head) {
        if(head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        Node cur = head;
        stack.push(cur);
        while (!stack.empty()) {
            cur = stack.pop();
            stack2.push(cur);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        while (!stack2.empty()) {
            System.out.print(stack2.pop().value + " ");
        }
        System.out.println();
    }

    public static void pos2(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node h = head;
        Node c = null;
        while (!stack.empty()) {
            c = stack.peek();
            if (c.left != null && h != c.left && h != c.right) {
                stack.push(c.left);
            } else if (c.right != null && h != c.right) {
                stack.push(c.right);
            } else {
                System.out.print(stack.pop().value + " ");
                h = c;
            }
        }
        System.out.println();
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
        pos1(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");


    }

}
