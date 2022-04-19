package com.jun.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @decription: 添加元素并监控集合大小
 * @date: 2022/4/19 11:25
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class AddAndMonitor {

    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new LinkedList<>());


        new Thread(() -> {
            synchronized(list) {
                try {
                    for (int i = 0; i < 10; i++) {
                        list.add((int) (Math.random() * 11));
                        log.info("加入的数：{}， 容器数量为: {}", i, list.size());
                        list.notify();
                        list.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()-> {
            synchronized (list) {
                try {
                while (true) {
                    if (list.size() == 5) {
                        log.info("容器数量为5");
                        list.notify();
                        break;
                    }
                    list.notify();
                    list.wait();
                }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
