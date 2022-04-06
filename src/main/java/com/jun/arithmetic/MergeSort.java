package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

/**
 * @decription: 归并算法
 * @date: 2022/4/6 14:05
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class MergeSort {


    /**
     * 递归方式实现
     * @param arr
     */
    public static void sort1Rec(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length -1);
    }

    public static void process1(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        // 【注意】位移运算符 优化级小于 +/-
        int mid = l + ((r - l) >> 1);
        log.info("mid: {}, l: {}, r: {}", mid, l, r);
        // 【注意】 入参数是动态的
        process1(arr, l, mid);
        // 【注意】 入参数是动态的
        process1(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int mid, int r) {
        // 【注意】 左开始指针
        int lp = l;
        // 【注意】 右开始指针
        int rp = mid + 1;
        // 【注意】大小是r -l + 1
        int[] helpArr = new int[r - l + 1];
        int index = 0;
        // 【注意】 边界，是<=，在有包含=的
        while( lp <= mid && rp <= r) {
            if (arr[lp] < arr[rp]) {
                helpArr[index++] = arr[lp++];
            } else {
                helpArr[index++] = arr[rp++];
            }
        }

        while (lp <= mid) {
            helpArr[index++] = arr[lp];
            lp++;
        }
        while (rp <= r) {
            helpArr[index++] = arr[rp];
            rp++;
        }

        for(int i=0; i < helpArr.length; i++) {
            // 【注意】 是从比较那个位置起保存的
            arr[l + i] = helpArr[i];
        }
    }

    // 非递归方式



}
