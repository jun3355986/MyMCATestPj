package com.jun.arithmetic.class15;

/**
 * @decription: 朋友圈问题，leetcode547
 * @date: 2022/8/8 10:30
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class Code01_FriendCircles {

    public static int findCircleNum(int[][] M) {
        int N = M.length;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (M[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets();
    }

    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sets;

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi-- ; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(int i , int j) {
            int aHead = find(i);
            int bHead = find(j);
            if (aHead != bHead) {
                if (size[aHead] >= size[bHead]) {
                    size[aHead] += size[bHead];
                    parent[bHead] = aHead;
                } else {
                    size[bHead] += size[aHead];
                    parent[aHead] = bHead;
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }

    }
}
