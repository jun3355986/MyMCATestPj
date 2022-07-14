package com.jun.arithmetic.class12;

import java.util.ArrayList;

/**
 * @className: Code02_IsBST
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/7/14 21:36
 **/
public class Code02_IsBST {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            value = data;
        }
    }

    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    public static class Info{
        public boolean isBST;
        public int min;
        public int max;

        public Info(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    public static Info process(Node n) {
        if (n == null) {
            return null;
        }
        Info leftInfo = process(n.left);
        Info rightInfo = process(n.right);
        boolean isBST = true;
        int min = n.value;
        int max = n.value;
        if (leftInfo != null ) {
            if (!leftInfo.isBST || leftInfo.max >= n.value) {
                isBST = false;
            }
            min = Math.min(leftInfo.min, min);
            max = Math.max(leftInfo.max, max);
        }
        if (rightInfo != null ) {
            if (!rightInfo.isBST || rightInfo.min <= n.value) {
                isBST = false;
            }
            min = Math.min(rightInfo.min, min);
            max = Math.max(rightInfo.max, max);
        }
        return new Info(isBST, min, max);
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
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
