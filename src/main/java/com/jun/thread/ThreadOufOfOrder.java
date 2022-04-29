package com.jun.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @decription: 乱序问题
 * @date: 2022/4/28 14:59
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class ThreadOufOfOrder {

    private static boolean ready = false;

    private static int number;

    private static int tt;

    private static class  ReaderThread extends Thread {

        @Override
        public void run() {
            while (!ready) {
//                Thread.yield();
                tt = 0;
            }
            log.info("number: {}", number);

        }

    }

    public static void main(String[] args) throws Exception {
        Thread t = new ReaderThread();
        t.start();
        number = 42;
        ready = true;
        t.join();
    }

}
