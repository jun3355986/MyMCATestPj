package com.jun.patterm.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @decription: 会说中文
 * @date: 2022/3/24 11:45
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class SpeakChinese implements SpeakLanguage{
    @Override
    public void iCanSpeak() {
        log.info("我会说中文");
    }
}
