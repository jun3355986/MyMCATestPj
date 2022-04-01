package com.jun.arithmetic;

import com.jun.common.innerclass.SortUtil;

/**
 * @decription: 冒泡排序
 * @date: 2022/4/1 18:05
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class BubblingSort {
    public static void sort(int[] arr) {
        // 注意排序顺序，后面是依次减少比较
        for (int i = 0 ; i < arr.length - 1; i++) {
            for (int j = 0 ; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    SortUtil.swap2(arr, j, j + 1);
                }
            }
        }
    }

}
