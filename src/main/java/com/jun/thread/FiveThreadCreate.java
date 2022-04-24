package com.jun.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @decription: 5种创建线程的方法
 * @date: 2022/4/24 11:41
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class FiveThreadCreate {

    static class Thread1 extends Thread {
        @Override
        public void run() {
            log.info("--------------每一类创建线程方式：继承Thread，重写run()方法--------------------");
        }
    }

    /**
     * 每一类创建线程方式：继承Thread，重写run()方法
     * 这种方法不好，因为使用了继承，接口就不能继承其他类了
     */
    public static void type1() {
        new Thread1().start();
    }


    static class Thread2 implements Runnable {

        @Override
        public void run() {
            log.info("--------------每二类创建线程方式：实现Runnable接口--------------------");
        }
    }

    /**
     *
     */
    public static void type2() {
        new Thread(new Thread2()).start();
    }

}
