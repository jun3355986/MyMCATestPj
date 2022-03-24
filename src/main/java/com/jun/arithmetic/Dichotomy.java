package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

/**
 * @className: Dichotomy
 * @description: 二分法
 * @author: jdt
 * @date: 2022/3/24 08:26
 **/
@Slf4j
public class Dichotomy {

    public static boolean find(int[] arr, int num) {
        log.info("------------------二分法查找------------------");
        int L = 0;
        int R = arr.length - 1;
        boolean res = false;
        while(L <= R) {
            int min = (L + R) / 2;
            if (arr[min] == num) {
                res = true;
                break;
            } else if (num > arr[min]) {
                L = min + 1;
            } else {
                R = min - 1;
            }
        }
        log.info("查找结果：{}", res);

        return res;
    }

    public static boolean genericFind(int[] arr, int num) {
        log.info("------------------直接轮询查找------------------");
        boolean res = false;
        for (int n : arr) {
            if (n == num) {
                res = true;
                break;
            }
        }
        log.info("查找结果：{}", res);

        return res;
    }

    /**
     * 有序数组中找到>=num最左的位置
     * @param num
     */
    public static int mostLeftNoLessNumIndex(int[] arr, int num) {
        log.info("------------------二分法查找 有序数组中找到>=num最左的位置------------------");
        int L = 0;
        int R = arr.length - 1;
        int ans = -1;
        while(L <= R) {
            int min = (L + R) / 2;
            if (arr[min] >= num) {
                R = min - 1;
                ans = min;
            } else {
                L = min + 1;
            }
        }
        log.info("查找结果：{}", ans);
        return ans;
    }


    /**
     * 局部最小问题
     * @param arr
     * @return
     */
    public static int oneMinIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int N = arr.length;
        if (N == 1) {
            return 0;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[N-1] < arr[N -2]) {
            return N -1;
        }
        int L = 0;
        int R = N -1;
        // L < R - 1 保证每次处理的L和R之间的数据有3个或以上，避免边界问题（arr[mid - 1]中 mid - 1 可能小于0）
        while (L < R - 1) {
            int mid = (L + R) / 2;
            // 此处理可能会出现边界问题，如果mid取到0时，就会出现边界问题，因此让L与R的距离大于2则可：L < R - 1
            if (arr[mid - 1] > arr[mid] && arr[mid] < arr[mid + 1]) {
                return mid;
            } else {
                if (arr[mid - 1] < arr[mid]) {
                    R = mid -1;
                } else {
                    L = mid + 1;
                }
            }
        }

        return arr[L] > arr[R] ? R : L;
    }

    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int)(Math.random() * maxLen);
        int[] arr = new int[len];
        if (len > 0) {
            arr[0] = (int)(Math.random() * maxValue);
            for(int i = 1; i < len; i++) {
                do {
                    arr[i] = (int)(Math.random() * maxValue);
                } while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
    }

    public static boolean check(int[] arr, int minIndex) {
        if (arr.length == 0) {
            return minIndex == -1;
        }
        int left = minIndex - 1;
        int right = minIndex + 1;
        boolean leftBigger = left < 0 || arr[left] > arr[minIndex];
        boolean rightBigger = right >= arr.length || arr[right] > arr[minIndex];
        return leftBigger && rightBigger;
    }






}
