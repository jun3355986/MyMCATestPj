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
            helpArr[index++] = arr[lp] < arr[rp] ? arr[lp++] : arr[rp++];
        }
        while (lp <= mid) {
            helpArr[index++] = arr[lp++];
        }
        while (rp <= r) {
            helpArr[index++] = arr[rp++];
        }
        for(int i=0; i < helpArr.length; i++) {
            // 【注意】 是从比较那个位置起保存的
            arr[l + i] = helpArr[i];
        }
    }

    // 非递归方式
    public static void sort2NoRec(int[] arr) {
        int step = 1;
        while (step < arr.length) {
            int l = 0;
            while(l < arr.length) {
                // 左组往右找的时候，如果刚好只能找到够左组或者不够左组数量的时候，停止
                if (step >= arr.length - l) {
                    break;
                }
                // 找出中间位置
                int m = l + step - 1;
                // 找出右组的r位置，可能会超过数组长度
                int r = Math.min(m + step , arr.length - 1);
                // 归并
                merge(arr, l, m , r);
                // 前一组（左右组）完成排序，下一组继续开始
                l = r + 1;
            }
            // 防止益处最大Integer.MAX_VALUE，因为数组长度大于Integer.MAX_VALUE/2的时候，以下步就会得出比Integer.MAX_VALUE的数
            if (step > arr.length / 2) {
                break;
            }
            step <<= 1;
        }
    }



}
