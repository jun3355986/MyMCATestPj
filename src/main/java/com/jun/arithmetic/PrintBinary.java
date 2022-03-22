package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

/**
 * @className: PrintBinary
 * @description: 打印二进制数出来
 * @author: jdt
 * @date: 2022/3/22 00:04
 **/
@Slf4j
public class PrintBinary {

    public static void printBinary(int num) {
        log.info(num + "的二进制编码是：");
        StringBuilder str = new StringBuilder();
        for (int i = 31; i >=0 ; i--) {
            str.append((num & (1 << i)) == 0 ? '0' : '1');
        }
        log.info(str.toString());
        log.info("");
    }

}
