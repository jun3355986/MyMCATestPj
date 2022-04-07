package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

/**
 * @className: CountOfRangeSum
 * @description: 计算数组中所有子数组的和在[lower, upper]范围的数量
 * @author: jdt
 * @date: 2022/4/7 23:31
 **/
@Slf4j
public class CountOfRangeSum {


    public static int count(int[] arr, int lower, int upper){
        if(arr == null ) {
            return 0;
        }
        return process(prefixSum(arr), 0, arr.length - 1, lower, upper);
    }

    /**
     * 计算数组的前缀数组
     * @param arr
     * @return
     */
    public static int[] prefixSum(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = res[Math.max(i - 1, 0)] + arr[i];
        }
        log.info("前缀数组：{}", res);
        return res;
    }

    public static int process(int[] arr, int l, int r, int lower, int upper) {
        if (l == r) {
            return arr[l] >= lower && arr[l] <= upper ? 1 : 0;
        }
        int m = l + ((r-l)>>1);
        return process(arr, l, m, lower, upper) + process(arr, m + 1, r, lower, upper) + merge(arr, l, m, r, lower, upper);
    }

    public static int merge(int[] arr, int l, int m, int r, int lower, int upper) {
        // 在左组滑动的窗口，左闭右开
        // 【注意】窗口刚开始的时候指针位置是一样的
        int lw = l;
        int rw = l;
        int sum = 0;
        for (int i = m+1; i <= r; i++) {
            int max = arr[i] - lower;
            int min = arr[i] - upper;
            while (rw <= m && arr[rw] <= max){
                // 往右走，直到找到超出上界，或超过m
                rw++;
            }
            while (lw <= m && arr[lw] < min) {
                // 往右走，直到刚好进入下界，或超过m - 1
                lw++;
            }
            sum += rw - lw;
        }

        int p1 = l;
        int p2 = m + 1;
        int[] help = new int[r - l + 1];
        int index = 0;
        while (p1 <= m && p2 <= r) {
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m ) {
            help[index++] = arr[p1++] ;
        }
        while ( p2 <= r) {
            help[index++] = arr[p2++];
        }
        for(int i = 0; i < help.length - 1; i++ ){
            arr[l + i] = help[i];
        }
        return sum;
    }


    public static class Comparator{

        public static int countRangeSum(int[] nums, int lower, int upper) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            long[] sum = new long[nums.length];
            sum[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                sum[i] = sum[i - 1] + nums[i];
            }
            log.info("前缀数组：{}", sum);
            return process(sum, 0, sum.length - 1, lower, upper);
        }

        public static int process(long[] sum, int L, int R, int lower, int upper) {
            if (L == R) {
                return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
            }
            int M = L + ((R - L) >> 1);
            return process(sum, L, M, lower, upper) + process(sum, M + 1, R, lower, upper)
                    + merge(sum, L, M, R, lower, upper);
        }

        public static int merge(long[] arr, int L, int M, int R, int lower, int upper) {
            int ans = 0;
            int windowL = L;
            int windowR = L;
            // [windowL, windowR)
            for (int i = M + 1; i <= R; i++) {
                long min = arr[i] - upper;
                long max = arr[i] - lower;
                while (windowR <= M && arr[windowR] <= max) {
                    windowR++;
                }
                while (windowL <= M && arr[windowL] < min) {
                    windowL++;
                }
                ans += windowR - windowL;
            }
            long[] help = new long[R - L + 1];
            int i = 0;
            int p1 = L;
            int p2 = M + 1;
            while (p1 <= M && p2 <= R) {
                help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
            }
            while (p1 <= M) {
                help[i++] = arr[p1++];
            }
            while (p2 <= R) {
                help[i++] = arr[p2++];
            }
            for (i = 0; i < help.length; i++) {
                arr[L + i] = help[i];
            }
            return ans;
        }
    }


}
