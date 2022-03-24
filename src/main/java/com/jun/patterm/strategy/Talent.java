package com.jun.patterm.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @decription: 学霸
 * @date: 2022/3/24 11:50
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class Talent implements Person{

    private DoSomeThing doSomeThing;
    private SpeakLanguage speakLanguage;

    public Talent(DoSomeThing doSomeThing, SpeakLanguage speakLanguage) {
        log.info("我是天才，我是学霸");
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
