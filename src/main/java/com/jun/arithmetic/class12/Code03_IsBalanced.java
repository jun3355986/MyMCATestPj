package com.jun.arithmetic.class12;

/**
 * @className: Code03_IsBalanced
 * @description: 平衡二叉树
 * @author: jdt
 * @date: 2022/7/14 12:56
 **/
public class Code03_IsBalanced {

    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isBalanced1(Node head) {
        // 作用是在递归中传递引用，引用被修改了会记录下来
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (head == null || !ans[0]) {
            // 【精妙】这里返回什么数都可以，因为所有节点高度都是从这里定义起点的，外部没有定义高度的（没有定义1个点高度为1还是0，还是100）
            // 定义高度是相对的，只有一个地方定义，没有其他外部定义，就不用同步高度标准
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }


    public static boolean isBalanced2(Node head) {
        return process(head).isBalanced;
    }

    public static class Info{
        public int height;
        public boolean isBalanced;
        public Info(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public static Info process(Node n) {
        if (n == null) {
            // 这里也是唯一定义高度的地方，height的起点不重要，可以定义-1，0，100，当然定义为0符合我们常规逻辑理解
            return new Info(100, true);
        }
        boolean isBalanced = true;
        Info leftInfo = process(n.left);
        Info rightInfo = process(n.right);
        if (!leftInfo.isBalanced
                || !rightInfo.isBalanced
                || Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalanced = false;
        }
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(height, isBalanced );

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
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
