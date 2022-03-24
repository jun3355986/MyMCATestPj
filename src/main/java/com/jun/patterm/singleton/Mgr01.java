package com.jun.patterm.singleton;

/**
 * @decription: 饿汉式
 * @date: 2022/3/24 10:20
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class Mgr01 {

    private static final Mgr01 INSTANCE = new Mgr01();

    private Mgr01() {}

    public static Mgr01 getInstance(){
        return INSTANCE;
    }
}
