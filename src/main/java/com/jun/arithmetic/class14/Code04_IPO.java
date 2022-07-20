package com.jun.arithmetic.class14;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @className: Code04_IPO
 * @description: 做项目取得最大受益
 * @author: jdt
 * @date: 2022/7/20 22:39
 **/
public class Code04_IPO {

    public static int findMaximizedCapital(int k , int W, int[] profits, int[] capital) {
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        for (int i=0; i< profits.length; i++) {
            minCostQ.add(new Program(profits[i], capital[i]));
        }
        for (int i = 0; i < k; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (!maxProfitQ.isEmpty()) {
                W = maxProfitQ.poll().p + W;
            } else {
                return W;
            }
        }
        return W;
    }

    public static class Program{
        public int p;
        public int c;
        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Program> {
        @Override
        public int compare(Program p1, Program p2) {
            return p1.c - p2.c;
        }
    }

    public static class MaxProfitComparator implements Comparator<Program> {
        @Override
        public int compare(Program p1, Program p2) {
            return p2.p - p1.p;
        }
    }
}
