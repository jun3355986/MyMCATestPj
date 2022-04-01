package com.jun.common.innerclass;

/**
 * @decription: 排序工具
 * @date: 2022/4/1 18:09
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class SortUtil {

    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }

    public static void swap2(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
