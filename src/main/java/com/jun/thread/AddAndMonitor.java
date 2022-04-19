package com.jun.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

/**
 * @decription: 添加元素并监控集合大小
 * @date: 2022/4/19 11:25
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class AddAndMonitor {

    public static void waitNotify() {

        List<Integer> list = Collections.synchronizedList(new LinkedList<>());

        new Thread(()-> {
            synchronized (list) {
                try {
                    list.wait();
                    log.info("容器数量为: {}", list.size());
                    list.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized(list) {
                try {
                    for (int i = 0; i < 10; i++) {
                        list.add((int) (Math.random() * 11));
                        log.info("加入的数：{}， 容器数量为: {}", list.get(i), list.size());
                        if (list.size() == 5) {
                            list.notify();
                            list.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void countDownLatchAwait() {
        List<Integer> list = Collections.synchronizedList(new LinkedList<>());
        CountDownLatch count = new CountDownLatch(1);
        CountDownLatch count2 = new CountDownLatch(1);



        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                list.add((int) (Math.random() * 11));
                log.info("加入的数：{}， 容器数量为: {}", list.get(i), list.size());
                if (list.size() == 5) {
                    try {
                        count.countDown();
                        count2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            try {
                count.await();
                log.info("容器数量为: {}", list.size());
                count2.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }

    static Thread t1 = null;
    static Thread t2 = null;

    public static void lockSupport() {
        List<Integer> list = Collections.synchronizedList(new LinkedList<>());

        t2 = new Thread(() -> {
            LockSupport.park();
            log.info("容器数量为: {}", list.size());
            LockSupport.unpark(t1);
        });

        t2.start();

        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                list.add((int) (Math.random() * 11));
                log.info("加入的数：{}， 容器数量为: {}", list.get(i), list.size());
                if (list.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        });

        t1.start();


    }

    public static void semaphore() {
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(1);
        List<Integer> list = Collections.synchronizedList(new LinkedList<>());



        t1 = new Thread(() -> {
            try {
                s1.acquire();
                for (int i = 0; i < 10; i++) {
                    list.add((int) (Math.random() * 11));
                    log.info("加入的数：{}， 容器数量为: {}", list.get(i), list.size());
                    if (list.size() == 5) {
                        s1.release();
                        s2.acquire();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        t2 = new Thread(() -> {
            try {
                s2.acquire();
                s1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("容器数量为: {}", list.size());
            s2.release();
        });

        t2.start();
    }


    public static void main(String[] args) {
//        waitNotify();
//        countDownLatchAwait();
//        lockSupport();
        semaphore();
    }

}
