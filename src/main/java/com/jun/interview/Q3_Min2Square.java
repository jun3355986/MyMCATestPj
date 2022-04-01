package com.jun.interview;

import lombok.extern.slf4j.Slf4j;

/**
 * @className: Q3_Min2Square
 * @description: 试题3
 * @author: jdt
 * @date: 2022/3/29 00:44
 **/
@Slf4j
public class Q3_Min2Square {

    public static int tableSizeFor(int n) {
        n--;

        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        log.info("n 构造后的值为：{}", n);
        return n < 0 ? 1 : n + 1;
    }
}
