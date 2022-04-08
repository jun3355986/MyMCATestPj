package com.jun.patterm.singleton;

/**
 * @decription: 第7种单例模式
 * @date: 2022/4/8 11:10
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class Mgr07_2 {

    private Mgr07_2() {}

    private static class Inner{
        private final static Mgr07_2 mgr07 = new Mgr07_2();
    }

    public static Mgr07_2 getInstance() {
        return Inner.mgr07;
    }

}
