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

    /**
     * 一个数组有一种数出现K次，其他数都出现了M次，M>1, K<M，找到出现k次的数，要求，额外空间复杂度为o(1)，时间复杂度为o(N)
     */
    public static int findKM(int[] arr, int k, int m) {


        if (arr == null || arr.length < 2) {
            return -1;
        }
        int[] sumArr = new int[32];

        // 【技巧】把int转二进制表示
        for (int n : arr) {
            for (int i = 0; i <= 31; i++) {
                sumArr[i] += 1 & (n >> i);
            }
        }

        int theK = 0;
        // 【技巧】把二进制转int表示
        for(int i = 0; i <= 31; i++) {
            theK += (sumArr[i] % m / k) << i;
        }
        return theK;

    }

}
