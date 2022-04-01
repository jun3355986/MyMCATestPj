package com.jun.arithmetic;

import com.jun.common.innerclass.SortUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @decription: 选择排序
 * @date: 2022/4/1 17:35
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class SelectSort {


    public static void sort(int[] arr) {
        int maxIndex = 0;
        for(int i=0; i< arr.length; i++) {
            maxIndex = i;
            for (int j = i + 1 ; j < arr.length; j++) {
                maxIndex = arr[maxIndex] < arr[j] ? j : maxIndex;
            }
            SortUtil.swap(arr, i, maxIndex);
        }

    }

}
