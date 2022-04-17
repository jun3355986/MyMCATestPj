package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @className: CoverMax
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/4/17 15:49
 **/
@Slf4j
public class CoverMax {

    public static int maxCover1(int[][] lines) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        for(int i =0; i < lines.length - 1; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        log.info("lines:find min max ..........");
        int cover = 0;
        for(double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length - 1; i++) {
                if (p > lines[i][0] && p < lines[i][1]) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    public static int maxCover2(int[][] lines) {
        Line[] lines1 = new Line[lines.length];
        for (int i = 0; i < lines.length; i++) {
            lines1[i] = new Line(lines[i][0], lines[i][1]);
        }
        Arrays.sort(lines1, (a, b) -> a.start - b.start);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (Line line : lines1) {
            while(!heap.isEmpty() && heap.peek() <= line.start) {
                heap.poll();
            }
            heap.add(line.end);
            max = Math.max(max, heap.size());
        }
        return max;

    }

    public static class Line {
        public int start;
        public int end;
        public Line(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

}
