package com.jun.arithmetic;

import com.jun.common.innerclass.SortUtil;
import sun.jvm.hotspot.debugger.linux.LinuxDebugger;

/**
 * @className: Heap
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/4/14 23:07
 **/
public class Heap {

    public static class MaxHeap {

        // 向大根堆中添加数据， 从最后的位置添加
        private void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1)/2]) {
                SortUtil.swap2(arr, index, (index - 1)/2);
                index = (index - 1)/2;
            }
        }

        private void heapify(int[] arr, int index, int heapSize){
            int left = index * 2 + 1;
            while (left < heapSize) {
                int largest = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
                largest = arr[largest] > arr[index] ? largest : index;
                if (largest == index) {
                    return;
                }
                SortUtil.swap2(arr, largest, index);
                index = largest;
                left = index * 2 + 1;
            }
        }
    }


}
