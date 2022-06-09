package com.jun.arithmetic.class11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @decription: 按层遍历二叉树
 * @date: 2022/5/25 11:21
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class LevelTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void level(Node head) {
        if(head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.value);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        level(head);
        System.out.println("========");
    }

}
