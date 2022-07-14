package com.jun.arithmetic.class12;

import java.util.ArrayList;

/**
 * @className: Code05_MaxSubBSTSize
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/7/14 23:25
 **/
public class Code05_MaxSubBSTSize {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for(int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i -1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node node, ArrayList<Node> arr) {
        if (node == null) {
            return;
        }
        in(node.left, arr);
        arr.add(node);
        in(node.right, arr);
    }

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
    }

    public static int maxSubBSTSize2(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxSubBSTSize;
    }

    public static class Info {
        public int maxSubBSTSize;
        public int nodes;
        public int min;
        public int max;

        public Info (int maxSubSize, int size, int min, int max) {
            maxSubBSTSize = maxSubSize;
            nodes = size;
            this.min = min;
            this.max = max;
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int min = head.value;
        int max = head.value;
        int nodes = 1;

        int p1 = -1;
        boolean isLeftBST = true;
        int leftMax = head.value - 1;
        int leftAll = 0;
        if (left != null) {
            min = Math.min(min, left.min);
            max = Math.max(max, left.max);
            nodes += left.nodes;

            p1 = left.maxSubBSTSize;

            isLeftBST = left.maxSubBSTSize == left.nodes;
            leftAll = left.nodes;
            leftMax = left.max;
        }
        int p2 = -1;
        boolean isRightBST = true;
        int rightMin = head.value + 1;
        int rightAll = 0;
        if (right != null) {
            min = Math.min(min, right.min);
            max = Math.max(max, right.max);
            nodes += right.nodes;

            p2 = right.maxSubBSTSize;

            isRightBST = right.maxSubBSTSize == right.nodes;
            rightAll = right.nodes;
            rightMin = right.min;
        }
        int p3 = -1;
        // 左子树是搜索二叉树而且又子树也是搜索二叉树
        if (isLeftBST && isRightBST) {
            if (leftMax < head.value && head.value < rightMin) {
                p3 = leftAll + rightAll + 1;
            }
        }

        return new Info(Math.max(p1, Math.max(p2, p3)), nodes, min, max);

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
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
