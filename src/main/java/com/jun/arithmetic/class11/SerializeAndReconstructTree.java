package com.jun.arithmetic.class11;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @decription: 先序，后序，按层序列化和反序列化
 * @date: 2022/5/25 11:00
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
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
    public static Queue<String> preSerial( Node node) {
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

    // 后序 序列化
    public static Queue<String> posSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        poss(head, queue);
        return queue;
    }

    public static void poss(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            poss(head.left, ans);
            poss(head.right, ans);
            ans.add(String.valueOf(head.value));
        }
    }

    // 后序 反序列化
    public static Node buildByPosQueue(Queue<String> poslist) {
        if (poslist == null || poslist.size() == 0) {
            return null;
        }
        // 左右中 -> 中右左
        Stack<String> stack = new Stack<>();
        while (!poslist.isEmpty()) {
            stack.push(poslist.poll());
        }
        return posb(stack);
    }

    public static Node posb(Stack<String> stack) {
        String data = stack.pop();
        if (data == null) {
            return null;
        } else {
            Node head = buildNode(data);
            head.right = posb(stack);
            head.left = posb(stack);
            return head;
        }
    }

    // 中序 序列化
    // 中序 反序列化

    // 按层 序列化
    public static Queue<String> levelSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur == null) {
                ans.add(null);
            } else {
                ans.add(String.valueOf(cur.value));
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        return ans;
    }


    // 按层 反序列化
    public static Node buildByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.isEmpty()) {
            return null;
        }
        Node head = buildNode(levelList.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.add(head);
        }
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            cur.left = buildNode(levelList.poll());
            if (cur.left != null) {
                queue.add(cur.left);
            }
            cur.right = buildNode(levelList.poll());
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return head;
    }


    // ==================================

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

    // for test
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    // for test
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = preSerial(head);
            Queue<String> pos = posSerial(head);
            Queue<String> level = levelSerial(head);
            Node preBuild = buildByPreQueue(pre);
            Node posBuild = buildByPosQueue(pos);
            Node levelBuild = buildByLevelQueue(level);
            if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");
        
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.left.right.left = new Node(8);
        head.left.right.right = new Node(9);
        head.left.right.right.right = new Node(10);

        log.info("list: {}",levelSerial(head));

        System.out.println("========");

    }

}
