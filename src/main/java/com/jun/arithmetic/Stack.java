package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

import java.util.EmptyStackException;

/**
 * @className: Stack
 * @description: 栈
 * @author: jdt
 * @date: 2022/4/2 21:54
 **/
@Slf4j
public class Stack {

    // 先进后出，只有栈头弹出和放入
    private Linked.DoubleNode[] arr;
    private int index = 0;

    private int size = 0;
    private final int MAX_SIZE;
    private Linked.DoubleNode stackTop = null;

    public Stack(int size) {
        this.MAX_SIZE = size;
        arr = new Linked.DoubleNode[size];
    }

    public Linked.DoubleNode getStackTop() {
        return this.stackTop;
    }


    // 双向链表实现---------------------------------------

    /**
     * 压栈
     * @param s
     */
    public void push(String s) {
        Linked.DoubleNode node = new Linked.DoubleNode(s);
        if (isEmpty()) {
            stackTop = node;
            size++;
            return;
        } else if (isFull()) {
            throw new StackOverflowError();
        }

        stackTop.setNext(node);
        node.setLast(stackTop);
        stackTop = node;
        size++;
    }

    /**
     * 弹出
     */
    public Linked.DoubleNode pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Linked.DoubleNode node = null;
        node = stackTop;
        if (size == 1){
            stackTop = null;
        } else {
            stackTop = node.getLast();
            stackTop.setNext(null);
            node.setLast(null);
        }
        size--;
        log.info("弹出：{}", node.d);
        return node;
    }

    public Linked.DoubleNode peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stackTop;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == MAX_SIZE;
    }


    public static void printStack(Stack stackTop) {
        StringBuilder sb = new StringBuilder();
        Linked.DoubleNode tail = stackTop.stackTop;
        while (tail != null) {
            sb.append(tail.d).append(",");
            tail = tail.getLast();
        }
        log.info("栈大小：{}, 内容：{}", stackTop.size, sb);

    }

    // 数组实现---------------------------------------




    /**
     * 压栈
     * @param s
     */
    public void pushArr(String s) {
        if (isFull()) {
            throw new StackOverflowError();
        }
        Linked.DoubleNode node = new Linked.DoubleNode(s);
        arr[index++] = node;
        size++;
    }

    /**
     * 弹出
     */
    public Linked.DoubleNode popArr() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Linked.DoubleNode node = arr[--index];
        size--;
        log.info("弹出：{}", node.d);
        return node;
    }

    public static void printStackArr(Stack stackTop) {
        StringBuilder sb = new StringBuilder();
        Linked.DoubleNode[] arr = stackTop.arr;

        for(int i = stackTop.index - 1 ; i >= 0 ; i-- ) {
            sb.append(arr[i].d).append(",");
        }
        log.info("栈大小：{}, index: {}, 内容：{}", stackTop.size, stackTop.index, sb);

    }

}
