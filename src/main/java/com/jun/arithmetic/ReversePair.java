package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

/**
 * @className: ReversePair
 * @description: 逆序对
 * @author: jdt
 * @date: 2022/4/7 02:05
 **/
@Slf4j
public class ReversePair {

    public static int count(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length -1);
    }

    public static int process(int[] arr, int l, int r){
        if (l == r) {
            return 0;
        }
        int mid = l + ((r-l)>>1);
        return process(arr, l, mid) + process(arr, mid + 1,  r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = help.length - 1;
        int lp = m;
        int rp = r;
        int count = 0;
        //【注意】左指针的边界是l，这里右边的条件按写错了，应该是rp，关于这些算法的变量，不要用相似的，搞一些区分度大的，不要排查错误搞晕自己
        while (lp >= l && rp >m) {
            //【注意】 如果左边大，有序对是rp - m
            count += arr[lp] > arr[rp] ? (rp - m) : 0;
            help[i--] = arr[lp] > arr[rp] ? arr[lp--] : arr[rp--];
        }
        while (lp >= l) {
            help[i--] = arr[lp--];
        }
        while (rp > m) {
            help[i--] = arr[rp--];
        }
        // 【注意】 help此时已填满，可以从0开始copy到arr
        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
        return count;

    }

}
