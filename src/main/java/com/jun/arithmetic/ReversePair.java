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
        return process(arr, l, mid) + process(arr, mid, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int m, int r) {
        int lp = m;
        int rp = r;
        int count = 0;
        int[] help = new int[r - l + 1];
        int i = 0;
        while (lp >= 0 && r >= m + 1) {
            count += arr[lp] < arr[rp] ? 0 : 1;
            help[i++] = arr[lp] < arr[rp] ? arr[lp++] : arr[rp++];
        }
        while (lp >= 0) {
            help[i++] = arr[lp++];
        }
        while (r >= m + 1) {
            help[i++] = arr[rp++];
        }
        for(int j = 0; j < help.length; j++) {
            arr[]help[j];
        }

    }

}
