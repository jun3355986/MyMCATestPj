package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

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

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    private static void process2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] range = netherlandsFlag(arr, l, r);
        // 【注意】没有移动时，m - 1可能会比l小
        process1(arr, l, range[0] - 1);
        process1(arr, range[1] + 1, r);
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    private static void process3(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] range = netherlandsFlag(arr, l + (int)(Math.random() * (r-l + 1) ), r);
        process1(arr, l, range[0] - 1);
        process1(arr, range[1] + 1, r);
    }

    public static class Op{
        public int l;
        public int r;

        public Op(int left, int right) {
            l = left;
            r = right;
        }
    }

    public static void quickSort4(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        swap(arr, (int)(Math.random() * N ), N - 1);
        int[] equalArea = netherlandsFlag(arr, 0, N - 1);
        int el = equalArea[0];
        int er = equalArea[1];
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(0, el - 1));
        stack.push(new Op(er + 1, N - 1));
        while (!stack.isEmpty()) {
            Op op = stack.pop();
            if (op.l < op.r) {
                swap(arr, op.l + (int)(Math.random()*(op.r - op.l + 1)), op.r);
                int[] equalArr =  netherlandsFlag(arr, op.l, op.r);
                stack.push(new Op(op.l, equalArr[0] - 1));
                stack.push(new Op(equalArr[1] + 1, op.r));
            }
        }
    }


}
