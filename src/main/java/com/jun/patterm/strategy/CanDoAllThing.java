package com.jun.patterm.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @decription: 什么都会做
 * @date: 2022/3/24 11:48
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class CanDoAllThing implements DoSomeThing{
    @Override
    public void iCanDo() {
        log.info("什么都会做");
    }
}
