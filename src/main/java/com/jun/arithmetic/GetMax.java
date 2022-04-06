package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

/**
 * @className: GetMax
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/4/4 13:08
 **/
@Slf4j
public class GetMax {

    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length -1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int mid = (l + r) >> 1;
        int lMax = process(arr, l, mid);
        int rMax = process(arr, mid + 1, r);
        return Math.max(lMax, rMax);
    }
}
