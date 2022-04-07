package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

/**
 * @className: SmallSum
 * @description: 小和问题
 * @author: jdt
 * @date: 2022/4/7 01:29
 **/
@Slf4j
public class SmallSum {

    public static int sum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {

        if(l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr,l, mid)
                + process(arr, mid + 1, r)
                + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int m, int r) {
        int lp = l;
        int rp = m + 1;
        int[] help = new int[r - l + 1];
        int index = 0;
        int res = 0;
        while (lp <= m && rp <= r) {
            res += arr[lp] < arr[rp] ? arr[lp] * (r - rp + 1) : 0;
            help[index++] = arr[lp] < arr[rp] ? arr[lp++] : arr[rp++];
        }
        while (lp <= m) {
            help[index++] =  arr[lp++];
        }
        while (rp <= r) {
            help[index++] =  arr[rp++];
        }
        for(int i=0; i < help.length ; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }

    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

}
