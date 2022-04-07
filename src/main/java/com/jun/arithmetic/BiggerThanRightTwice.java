package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

/**
 * @decription: 左边的数比右边的数2倍还大
 * @date: 2022/4/7 18:59
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class BiggerThanRightTwice {

    public static int count(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l +( (r - l) >> 1);
        return process(arr, l, m) + process(arr, m + 1, r) + merge(arr, l, m, r);
    }

    public static int merge(int[] arr, int l, int m, int r) {
        int rp = m+1;
        int sum = 0;
        for(int i = l; i <= m; i++) {
            // 【注意】这里是不越界的前提做判断是否大于右边两倍
            while (rp <= r && arr[i] > (arr[rp] << 1) ) {
                log.info("i: {}, rp: {}, r: {}", i, rp, r);
                rp++;
            }
            sum += rp - m - 1;
        }

        int lp = l;
        rp = m + 1;
        int[] help = new int[r - l + 1];
        int index = 0;
        while (lp <= m && rp <= r) {
            help[index++] = arr[lp] < arr[rp] ? arr[lp++] : arr[rp++];
        }

        while (lp <= m) {
            help[index++] = arr[lp++];
        }

        while (rp <= r) {
            help[index++] = arr[rp++];
        }
        for(int i=0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return sum;
    }

    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
