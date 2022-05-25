package com.jun.arithmetic.class11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @decription: 先序，后序，按层序列化和反序列化
 * @date: 2022/5/25 11:00
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class SerializeAndReconstructTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 使用递归方式实现
    // 先序 序列化
    public static Queue<String> perSerial( Node node) {
        Queue<String> ans = new LinkedList<>();
        pres(node, ans);
        return ans;
    }

    public static void pres(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            pres(head.left, ans);
            pres(head.right, ans);
        }

    }

    // 先序 反序列化
    public static Node buildByPreQueue(Queue<String> prelist) {
        if (prelist == null || prelist.isEmpty()) {
            return null;
        }
        return preb(prelist);
    }

    public static Node preb(Queue<String> prelist) {
        String data = prelist.poll();
        if (data == null) {
            return null;
        }
        Node ans = buildNode(data);
        ans.left = preb(prelist);
        ans.right = preb(prelist);
        return ans;
    }

    public static Node buildNode(String value) {
        if (value == null) {
            return null;
        }
        return new Node(Integer.parseInt(value));
    }
}
