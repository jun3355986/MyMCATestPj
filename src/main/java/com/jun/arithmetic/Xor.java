package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

/**
 * @className: Xor
 * @description: 异或运算相关
 * @author: jdt
 * @date: 2022/4/1 23:42
 **/
@Slf4j
public class Xor {

    /**
     * 在一堆数中，只有两种数，一种是数是奇数个数，一种是偶数个数，找出奇数
     * @return
     */
    public static int findOddInArr(int[] arr){

        int res = 0;
        for(int d : arr) {
            res ^= d;
        }
        return res;
    }

    /**
     * 找出一个int最右侧的1   000100  111011  111100
     * @return
     */
    public static int findLastRightOne(int n) {
        return n &= -n;
    }

    /**
     * 一堆数，有两种数为奇数个，其他都为偶数，找出这两个奇数
     * @param arr
     */
    public static int[] findTwoOdd(int[] arr) {

        int[] res = new int[2];
        if (arr == null || arr.length < 3) {
            res  = arr;
        }

        int s = 0;
        int a = 0;
        int b = 0;
        for(int n : arr) {
            s ^= n;
        }
        int rightOne = s & -s;

        for(int n : arr) {
            if ((rightOne & n) != 0) {
                a ^= n;
            }
        }

        b = s ^ a;
        res[0] = a;
        res[1] = b;

        return res;

    }

}
