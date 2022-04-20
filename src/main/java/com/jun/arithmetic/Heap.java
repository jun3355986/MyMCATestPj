package com.jun.arithmetic;

import com.jun.common.innerclass.SortUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @className: Heap
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/4/14 23:07
 **/
@Slf4j
public class Heap {

    public static class MaxHeap {

        private int[] heap;
        private final int limit;
        private int heapSize;

        public int[] getHeap() {
            return this.heap;
        }

        public MaxHeap(int limit){
            this.limit = limit;
            heap = new int[limit];
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        public int pop() {
            int ans = heap[0];
            SortUtil.swap2(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;
        }

        // 向大根堆中添加数据， 从最后的位置添加
        public static void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1)/2]) {
                SortUtil.swap2(arr, index, (index - 1)/2);
                index = (index - 1)/2;
            }
        }

        public static void heapify(int[] arr, int index, int heapSize){
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

    public static class MinHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MinHeap(int limit){
            this.limit = limit;
            heap = new int[limit];
        }

        public int[] getHeap() {
            return this.heap;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        public int pop() {
            int ans = heap[0];
            SortUtil.swap2(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;
        }

        // 向大根堆中添加数据， 从最后的位置添加
        public static void heapInsert(int[] arr, int index) {
            while (arr[index] < arr[(index - 1)/2]) {
                SortUtil.swap2(arr, index, (index - 1)/2);
                index = (index - 1)/2;
            }
        }

        public static void heapify(int[] arr, int index, int heapSize){
            int left = index * 2 + 1;
            while (left < heapSize) {
                int less = left + 1 < heapSize && arr[left] > arr[left + 1] ? left + 1 : left;
                less = arr[less] < arr[index] ? less : index;
                if (less == index) {
                    return;
                }
                SortUtil.swap2(arr, less, index);
                index = less;
                left = index * 2 + 1;
            }
        }
    }

    public static void printTree(int[] arr) {
        log.info("-------------------start--------------------");
        if (arr == null){
            return;
        }
        int layer = 0;
        int from = 0;
        int to = 1;
        while( to <= arr.length) {
            to = from + (int)Math.pow(2, layer);
//            log.info("layer: {}, from: {}, to: {}", layer, from, to );
            int[] subArr = Arrays.copyOfRange(arr,  from, Math.min(to, arr.length));
            log.info("{}",subArr);
            from = to ;
            ++layer;
        }
        log.info("-----------------end----------------------");
    }

    public static void printTree(Integer[] arr) {
        printTree(Arrays.stream(arr).mapToInt(num -> num == null ? 0 : num).toArray());
    }

    public static void printTree(List<Integer> arr) {
        printTree(arr.toArray(new Integer[0]));
    }


}
