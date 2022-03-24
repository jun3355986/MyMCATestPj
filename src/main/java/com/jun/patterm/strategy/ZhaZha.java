package com.jun.patterm.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @decription: 渣渣 什么都不会做
 * @date: 2022/3/24 11:47
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class ZhaZha implements Person {
    private DoSomeThing doSomeThing;
    private SpeakLanguage speakLanguage;

    public ZhaZha(DoSomeThing doSomeThing, SpeakLanguage speakLanguage) {
        log.info("我是渣渣");
        this.doSomeThing = doSomeThing;
        this.speakLanguage = speakLanguage;
    }

    @Override
    public void iCanDo() {
        doSomeThing.iCanDo();
    }

    @Override
    public void iCanSpeak() {
        speakLanguage.iCanSpeak();
    }
}
