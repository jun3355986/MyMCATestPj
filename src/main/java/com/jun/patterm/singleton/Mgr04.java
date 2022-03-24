package com.jun.patterm.singleton;

/**
 * @decription: 懒汉式
 * @date: 2022/3/24 10:23
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class Mgr04 {

    private static Mgr04 INSTANCE = null;

    private Mgr04() {}

    public static synchronized Mgr04 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr04();
        }
        return INSTANCE;
    }

}
