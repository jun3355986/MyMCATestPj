package com.jun.patterm.singleton;

/**
 * @decription: 第7种方式
 * @date: 2022/3/22 10:48
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class Mgr07 {
    private Mgr07() {}

    private static Class Mgr07Holder {
        private final static Mgr07 INSTANCE = new Mgr07();
    }

    public static Mgr07 getInstance() {
        return Mgr07Holder.I
    }
}
