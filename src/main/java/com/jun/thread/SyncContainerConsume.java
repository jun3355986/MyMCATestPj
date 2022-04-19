package com.jun.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @decription: 一个同步容器，有2个生产者，10个消费者，控制生产与消费
 * @date: 2022/4/19 17:37
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class SyncContainerConsume<T> {

    final LinkedList<T> list = new LinkedList<>();
    final int MAX = 10;
    int count = 0;

    public synchronized void put(T t) {
        while (list.size() == MAX) {
            try {
                log.info("容器已满！！！！！！！！！！！！！！！");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();;
            }
        }
        count++;
        list.add(t);
        // 通知所有消费都消费
        this.notifyAll();
    }

    public synchronized T get() {
        T t = null;
        while (list.size() == 0) {
            try {
                log.info("容器已空（）（）（）（）（）（）（）");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.removeFirst();
        count --;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {

        SyncContainerConsume<Integer> scc = new SyncContainerConsume();
        for(int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    while (true) {
                        // 睡眠随机时间0~3s
                        Thread.sleep((long) (Math.random() * 3000));
                        scc.put((int) (Math.random() * 100));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for(int i = 0; i < 1; i++) {
            new Thread(()->{
                try {
                    while (true) {
                        // 睡眠随机时间0~3s
                        Thread.sleep((long) (Math.random() * 1000));
                        log.info("<<<<<<<<<<<<<<<<thread: {}, get: {}", Thread.currentThread(), scc.get());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

}
