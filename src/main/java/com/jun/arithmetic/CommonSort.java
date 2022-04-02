package com.jun.arithmetic;

import com.jun.common.innerclass.SortUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @className: CommonSort
 * @description: 一般的排序算法
 * @author: jdt
 * @date: 2022/4/1 21:14
 **/
@Slf4j
public class CommonSort {

    /**
     * 插入排序
     * @param arr
     */
    public static void insertionSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i=1; i < arr.length; i++) {
            // 注意啊，for循环是在执行循环体之前判断语句的
            for(int j = i - 1; j >= 0 && arr[j] < arr[j + 1]; j--) {
                SortUtil.swap(arr, j, j+1);
            }
        }
    }

    private static boolean isBigger(int[] arr, int j, int i) {

        boolean res =  arr[j] < arr[j + 1];
        log.info("i: {}, j: {}, res: {}", i , j, res);
        return res;
    }

}
