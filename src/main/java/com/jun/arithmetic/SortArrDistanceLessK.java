package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;

/**
 * @className: SortArrDistanceLessK
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/4/17 13:45
 **/
@Slf4j
public class SortArrDistanceLessK {

    public static void sort(int[] arr, int k) {
        if (k == 0) {
            return;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;

        for(; index <= Math.min(arr.length - 1, k - 1); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for(; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }


}
