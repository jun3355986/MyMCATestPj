package com.jun.patterm.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @decription: 会说英语
 * @date: 2022/3/24 11:44
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class SpeakEnglish implements SpeakLanguage{
    @Override
    public void iCanSpeak() {
        log.info("我会说英语");
    }
}
