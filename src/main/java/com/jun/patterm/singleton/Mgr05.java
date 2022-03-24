package com.jun.patterm.singleton;

/**
 * @decription: 懒汉式
 * @date: 2022/3/24 10:23
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class Mgr05 {

    private static Mgr05 INSTANCE = null;

    private Mgr05() {}

    public static Mgr05 getInstance() {
        if (INSTANCE == null) {
            synchronized (Mgr05.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Mgr05();
                }
            }
        }
        return INSTANCE;
    }

}
