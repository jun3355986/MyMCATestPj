package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * @className: QueueImpByStack
 * @description: 使用栈实现队列
 * @author: jdt
 * @date: 2022/4/4 11:30
 **/
@Slf4j
public class QueueImpByTwoStack {

    private int size = 0;
    private int limit = 0;

    public QueueImpByTwoStack(int limit) {
        this.limit  = limit;
    }

    // 栈实现队列
    private Stack<Integer> pushStack = new Stack();
    private Stack<Integer> popStack = new Stack();

    public void add(Integer i) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        pushStack.add(i);
        size++;
    }

    public Integer remove() {
        if (isEmpty()) {
            throw new RuntimeException("队列已满");
        }
        if (popStack.isEmpty()) {
            pushToPop();
        }
        Integer i = popStack.pop();
        log.info("导出头部元素: {}", i);
        return i;
    }

    public void pushToPop() {
        while (!pushStack.isEmpty()) {
            popStack.push(pushStack.pop());
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == limit;
    }
}
