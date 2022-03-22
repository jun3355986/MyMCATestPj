package com.jun.patterm.singleton;

/**
 * @className: Mgr07
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/3/22 13:01
 **/
public class Mgr07 {
    private Mgr07() {}

    private static class Mgr07Holder {
        private final static Mgr07 INSTANCE = new Mgr07();
    }

    public static Mgr07 getInstance() {
        return Mgr07Holder.INSTANCE;
    }
}
