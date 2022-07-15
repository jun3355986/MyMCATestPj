package com.jun.arithmetic.class12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @className: Code06_MaxDistance
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/7/15 09:07
 **/
public class Code06_MaxDistance {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 暴力解法
     * @param head
     * @return
     */
    public static int maxDistance1(Node head) {
        if (head == null ) {
            return 0;
        }
        ArrayList<Node> arr = getPrelist(head);
        HashMap<Node, Node> parentMap = getParentMap(head);
        int max = 0;
        for (int i = 0; i < arr.size(); i++) {
            for(int j = i; j < arr.size(); j++) {
                max = Math.max(max, distance(parentMap, arr.get(i), arr.get(j)));
            }
        }
        return max;
    }

    public static ArrayList<Node> getPrelist(Node head) {
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        return arr;
    }

    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static HashMap<Node, Node> getParentMap(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        map.put(head, null);
        fillParentMap(head, map);
        return map;
    }

    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    public static int distance(HashMap<Node, Node> parentNode, Node o1, Node o2) {
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(o1);
        while (parentNode.get(cur) != null) {
            cur = parentNode.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentNode.get(cur);
        }
        Node lowestAncestor = cur;
        cur = o1;
        int distance1 = 1;
        while (cur != lowestAncestor) {
            cur = parentNode.get(cur);
            distance1++;
        }
        cur = o2;
        int distance2 = 1;
        while (cur != lowestAncestor) {
            cur = parentNode.get(cur);
            distance2++;
        }
        return distance1 + distance2 - 1;

    }

    // 二叉树套路
    public static int maxDistance2(Node head) {
        if (head == null) {
            return 0;
        }
        Info info = process(head);
        return Math.max(info.leftXDistance + info.rightXDistance - 1
                ,Math.max(info.leftMaxDistance, info.rightMaxDistance));
    }

    public static class Info{
        public int leftMaxDistance;
        public int rightMaxDistance;
        public int leftXDistance;
        public int rightXDistance;

        public Info(int leftMD, int rightMD, int leftXD, int rightXD) {
            leftMaxDistance = leftMD;
            rightMaxDistance = rightMD;
            leftXDistance = leftXD;
            rightXDistance = rightXD;
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0, 0, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int leftMaxDistance = Math.max(leftInfo.leftXDistance + leftInfo.rightXDistance - 1
                ,Math.max(leftInfo.leftMaxDistance, leftInfo.rightMaxDistance));
        int rightMaxDistance = Math.max(rightInfo.leftXDistance + rightInfo.rightXDistance - 1
                ,Math.max(rightInfo.leftMaxDistance, rightInfo.rightMaxDistance));;
        int leftXDistance = Math.max(leftInfo.leftXDistance , leftInfo.rightXDistance ) + 1;
        int rightXDistance = Math.max(rightInfo.leftXDistance , rightInfo.rightXDistance ) + 1;
        return new Info(leftMaxDistance, rightMaxDistance, leftXDistance, rightXDistance);
    }

    // 老师的方式
    public static int maxDistance3(Node head) {
        if (head == null) {
            return 0;
        }
        return process2(head).maxDistance;
    }

    public static class Info2{
        public int maxDistance;
        public int height;
        public Info2(int maxD, int h) {
            maxDistance = maxD;
            height = h;
        }
    }

    public static Info2 process2(Node head){
        if (head == null) {
            return new Info2(0, 0);
        }
        Info2 leftInfo = process2(head.left);
        Info2 rightInfo = process2(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDistance = Math.max(leftInfo.height + rightInfo.height + 1
                ,Math.max(leftInfo.maxDistance, rightInfo.maxDistance));
        return new Info2(maxDistance, height);
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
            if (maxDistance1(head) != maxDistance3(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
