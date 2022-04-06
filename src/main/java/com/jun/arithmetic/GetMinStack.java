package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * @className: GetMinStack
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/4/4 10:08
 **/
@Slf4j
public class GetMinStack {

    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int i) {

        if (minStack.empty()) {
            minStack.push(i);
        } else if (i <= getMin()) {
            minStack.push(i);
        } else {
            minStack.push(minStack.peek());
        }
        stack.push(i);
    }

    public Integer pop() {
        if (this.stack.isEmpty()) {
            throw new RuntimeException("栈空了");
        }
        minStack.pop();
        return stack.pop();
    }

    public Integer getMin() {
        if (this.minStack.isEmpty()) {
            throw new RuntimeException("栈空了");
        }
        return minStack.peek();
    }

}
