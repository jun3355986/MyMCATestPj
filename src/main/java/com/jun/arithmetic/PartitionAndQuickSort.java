package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

/**
 * @className: PartitionAndQuickSort
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/4/13 23:34
 **/
@Slf4j
public class PartitionAndQuickSort {


    public static void swap(int[] arr, int i, int j) {
//        arr[i] ^= arr[j];
//        arr[j] ^= arr[i];
//        arr[i] ^= arr[j];
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    // arr[L..R]上，以arr[R]位置的数做划分值
    // <= X > X
    // <= X X
    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = L - 1;
        int p = L;
        while(p < R) {
            if (arr[p] <= arr[R]) {
                swap(arr, ++lessEqual, p);
            }
            p++;
        }
        swap(arr, ++lessEqual, R);
        return lessEqual;
    }

    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    // <arr[R] ==arr[R] > arr[R]
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;
        int more = R;
        int index = L;
        while(index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, ++less, index++);
            } else {
                // 如果大于，就不能index++，因为把前面的数换到当前位置，前面的数是没比较过的
                swap(arr, --more, index);
            }
        }
        swap(arr, R, more);
        // 返回左闭右闭区间，因为有上面的交换，所以arr[more]== arr[R]
        return new int[]{less + 1, more};
    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    private static void process1(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int m = partition(arr, l, r);
        // 【注意】没有移动时，m - 1可能会比l小
        process1(arr, l, m - 1);
        process1(arr, m + 1, r);
    }


}
