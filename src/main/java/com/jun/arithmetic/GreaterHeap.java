package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @decription: 加强堆 这里做小根堆
 * @date: 2022/4/20 10:04
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class GreaterHeap<T> {

    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comp;

    public GreaterHeap(Comparator<T> c) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        comp = c;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return heap.contains(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T ans = peek();
        swap(0, heapSize - 1);
        // 记得删除弹出的元素，避免内容泄漏
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }

    public void remove(T obj) {
        int index = indexMap.get(obj);
        T replace = heap.get(heapSize - 1);
        indexMap.remove(obj);
        // 前面已保存最后一个，这里删除并减少heap大小没问题
        heap.remove(--heapSize);
        if (obj != replace) {
            // 把刚才删除的最后一个替换到删除的目标位置上
            indexMap.put(replace, index);
            heap.set(index, replace);
            resign(replace);
        }
    }

    public void resign(T object) {
        heapify(indexMap.get(object));
        heapInsert(indexMap.get(object));
    }

    public List<T> getAllElements() {
        return new ArrayList<>(heap);
    }

    /**
     * o(logN)
     * @param k
     */
    public void heapify(int k) {
        int left = k * 2 + 1;
        while (left < heapSize ) {
            // 【注意】这里比较是黑盒，注意比较器的参数顺序和结果中选择第几个，一般比较结果判断使用相同符号去判断
            int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1: left;
            best = comp.compare(heap.get(k), heap.get(best)) < 0 ? k : best ;
            if (best == k ) {
                break;
            }
            swap(best, k);
            k = best;
            left = k * 2 + 1;
        }
    }

    /**
     * o(logN)
     * @param k
     */
    public void heapInsert(int k) {
        while ((k - 1) / 2 >= 0 && comp.compare(heap.get(k), heap.get((k - 1) / 2)) < 0 ) {
            swap((k - 1) / 2, k);
            k = (k - 1) / 2;
        }
    }

    public void swap(int a, int b ) {
        T pre = heap.get(a);
        T pos = heap.get(b);
        indexMap.put(pre, b);
        indexMap.put(pos, a);
        heap.set(a, pos);
        heap.set(b, pre);
    }

}
