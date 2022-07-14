package com.jun.arithmetic.class12;

/**
 * @className: Code04_IsFull
 * @description: 满二叉树
 * @author: jdt
 * @date: 2022/7/14 22:37
 **/
public class Code04_IsFull {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isFull1(Node head) {
        if (head == null) {
            return true;
        }
        int height = h(head);
        int nodes = n(head);
        return (1 << height) - 1 == nodes;
    }

    public static int h(Node n ) {
        if (n == null) {
            return 0;
        }
        return Math.max(h(n.left), h(n.right)) + 1;
    }
    public static int n(Node n ) {
        if (n == null) {
            return 0;
        }
        return n(n.left) + n(n.right) + 1;
    }

    public static boolean isFull2(Node head) {
        if (head == null) {
            return true;
        }
        Info all = process(head);
        return (1 << all.height) - 1 == all.nodes;
    }


    public static class Info {
        public int height;
        public int nodes;
        public Info ( int h, int n) {
            height = h;
            nodes = n;
        }
    }

    public static Info process(Node node) {
        if (node == null) {
            return new Info( 0, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int h = Math.max(rightInfo.height, leftInfo.height) + 1;
        int n = rightInfo.nodes + leftInfo.nodes + 1;
        return new Info( h, n);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isFull1(head) != isFull2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
