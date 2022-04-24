package com.jun.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

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
     * 每一类创建线程方式：继承Thread，重写run()方法 实现runnable接口
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


    public static void type2() {
        new Thread(new Thread2()).start();
    }

    /**
     * 第三种方式 匿名内部类 跟第二种是一个类型 实现runnable接口
     */
    public static void type3() {
        new Thread(() -> {
            log.info("--------------每三类创建线程方式：匿名内部类--------------------");
        }).start();
    }

    static ExecutorService service = Executors.newCachedThreadPool();
    /**
     * 线程池方式 实现runnable接口
     */
    public static void type4() {

        service.execute(() -> {
            log.info("--------------每四类创建线程方式：线程池--------------------");
        });
    }


    static class Thread5 implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("--------------每五类创建线程方式：实现callable接口--------------------");
            return "success";
        }
    }

    /**
     * 第5类方式 实现callable接口, 一般配合Future使用
     */
    public static void type5() throws ExecutionException, InterruptedException {
        Future<String> f = service.submit(new Thread5());
        log.info("结果：{}", f.get());
    }

}
