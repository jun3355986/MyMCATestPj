package com.jun.arithmetic.class11;

import java.util.List;

/**
 * @className: EncodeNaryTreeToBinaryTree
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/6/9 12:33
 **/
public class EncodeNaryTreeToBinaryTree {

    public static class Node {
        public int val ;
        public List<Node> children;
        public Node() {}

        public Node(int val) {
            this.val = val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }
        TreeNode head = new TreeNode(root.val);
        head.left = en(root.children);
        return head;
    }

    private TreeNode en(List<Node> children) {
        TreeNode head = null;
        TreeNode cur = null;
        for (Node child : children) {
            TreeNode tNode = new TreeNode(child.val);
            if (head == null) {
                head = tNode;
            } else {
                cur.right = tNode;
            }
            cur = tNode;
            cur.left = en(child.children);
        }
        return head;
    }
}
