package com.jun.arithmetic.class12;

import java.util.LinkedList;

/**
 * @className: IsCBT
 * @description: 判断一树二叉树是否是完全二叉树
 * @author: jdt
 * @date: 2022/6/10 08:19
 **/
public class IsCBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isCBT1 (Node head) {
        if (head == null ){
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        // 是否遇到过左右孩子不双全的节点
        boolean leaf = false;
        queue.add(head);
        while (!queue.isEmpty()) {
            Node h = queue.poll();
            Node l = h.left;
            Node r = h.right;
            if ((leaf && (l != null || r != null))
                    || (l == null && r !=null)) {
                return false;
            }
            if (l != null) {
                queue.add(r);
            }
            if (r != null) {
                queue.add(l);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int)(Math.random() * maxValue));
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
            //if (isCBT1())
        }
    }

}
