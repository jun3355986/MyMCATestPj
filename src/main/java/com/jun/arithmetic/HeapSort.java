package com.jun.arithmetic;

import com.jun.common.innerclass.SortUtil;

/**
 * @className: HeapSort
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/4/16 16:44
 **/
public class HeapSort {
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 从下到上 heapify o(N)
//        for (int i = arr.length - 1; i >= 0 ; i--) {
//            Heap.MaxHeap.heapify(arr, i, arr.length);
//        }

        // 从上到下 heapInsert o(N*logN)
        for(int i = 0; i < arr.length; i++) {
            Heap.MaxHeap.heapInsert(arr, i);
        }

        Heap.printTree(arr);

        // 每次拍好最在的数，然后数组-1
        int heapSize = arr.length;
        SortUtil.swap2(arr, 0, --heapSize);
        while (heapSize > 0) {
            Heap.MaxHeap.heapify(arr, 0, heapSize);
            SortUtil.swap2(arr, 0, --heapSize);
        }


    }
}
