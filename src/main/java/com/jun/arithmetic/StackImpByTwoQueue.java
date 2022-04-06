package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;


/**
 * @className: StackImpByTwoQueue
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/4/4 12:20
 **/
@Slf4j
public class StackImpByTwoQueue {

    private Queue queue1 = new Queue();
    private Queue queue2 = new Queue();

    public void push(String s) {
        if (queue2.isEmpty()) {
            queue1.add(s);
        } else {
            queue2.add(s);
        }
    }

    public String pop() {
        String res = null;

        if(queue1.isEmpty() && queue2.isEmpty()) {
            throw new RuntimeException("栈已经空了");
        }

        if (!queue1.isEmpty()) {
            res = getFromQueueLast(queue1, queue2);
        } else {
            res = getFromQueueLast(queue2, queue1);
        }
        log.info("栈弹出内容：{}", res);
        return res;
    }

    public String getFromQueueLast(Queue fromQ, Queue toQ) {
        while (fromQ.size > 1) {
            toQ.add(fromQ.remove().d);
        }
        return fromQ.remove().d;
    }

}
