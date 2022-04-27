package com.jun.arithmetic;

import java.util.Stack;

/**
 * @decription: 回型针结构
 * @date: 2022/4/27 10:35
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class IsPalindromeList {


    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 方式
    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while(cur != null) {
            stack.push(cur);
            cur  = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.value != stack.pop().value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    public static boolean isPalindrome2(Node node) {

    }

}
