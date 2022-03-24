package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

/**
 * @className: Dichotomy
 * @description: 二分法
 * @author: jdt
 * @date: 2022/3/24 08:26
 **/
@Slf4j
public class Dichotomy {

    public static boolean find(int[] arr, int num) {
        log.info("------------------二分法查找------------------");
        int L = 0;
        int R = arr.length - 1;
        boolean res = false;
        while(L <= R) {
            int min = (L + R) / 2;
            if (arr[min] == num) {
                res = true;
                break;
            } else if (num > arr[min]) {
                L = min + 1;
            } else {
                R = min - 1;
            }
        }
        log.info("查找结果：{}", res);

        return res;
    }

    public static boolean genericFind(int[] arr, int num) {
        log.info("------------------直接轮询查找------------------");
        boolean res = false;
        for (int n : arr) {
            if (n == num) {
                res = true;
                break;
            }
        }
        log.info("查找结果：{}", res);

        return res;
    }

    /**
     * 有序数组中找到>=num最左的位置
     * @param num
     */
    public static int mostLeftNoLessNumIndex(int[] arr, int num) {
        log.info("------------------二分法查找 有序数组中找到>=num最左的位置------------------");
        int L = 0;
        int R = arr.length - 1;
        int ans = -1;
        while(L <= R) {
            int min = (L + R) / 2;
            if (arr[min] >= num) {
                R = min - 1;
                ans = min;
            } else {
                L = min + 1;
            }
        }
        log.info("查找结果：{}", ans);
        return ans;
    }



}
