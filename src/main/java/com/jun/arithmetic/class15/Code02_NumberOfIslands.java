package com.jun.arithmetic.class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @decription: 孤岛问题1
 * @date: 2022/8/8 11:33
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class Code02_NumberOfIslands {


    public static int numIslands3(char[][] board) {
        int islands = 0;
        for(int i = 0; i < board.length; i++) {
            for (int j = 0 ; j < board[0].length ; j++) {
                if (board[i][j] == '1') {
                    islands++;
                    infect(board, i, j);
                }
            }
        }
        return islands;
    }

    public static void infect(char[][] board, int i, int j) {
        if (i < 0 || i == board.length || j < 0 || j == board.length || board[i][j] != '1') {
            return;
        }
        board[i][j] = 0;
        infect(board, i+1, j);
        infect(board, i-1, j);
        infect(board, i, j+1);
        infect(board, i, j-1);
    }

    public static int numIslands1(char[][] board) {
        int row = board.length;
        int col = board[0].length;

        Dot[][] dots = new Dot[row][col];
        List<Dot> dotList = new ArrayList<>();
        for(int i = 0; i < row ; i++) {
            for(int j= 0; j < col; j++) {
                if (board[i][j] == '1') {
                    dots[i][j] = new Dot();
                    dotList.add(dots[i][j]);
                }
            }
        }
        UnionFind1<Dot> unionFind1 = new UnionFind1<>(dotList);
        // 处理最上边的列边界问题
        for(int i = 1; i < col; i++) {
            if (board[0][i] == '1' && board[0][i-1] == '1') {
                unionFind1.union(dots[0][i], dots[0][i-1]);
            }
        }
        // 处理最左边的列边界问题
        for(int i = 1; i < row; i++) {
            if (board[i][0] == '1' && board[i-1][0] == '1') {
                unionFind1.union(dots[i][0], dots[i-1][0]);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if ((board[i][j] == '1' && board[i-1][j] == '1') ) {
                    unionFind1.union(dots[i][j], dots[i-1][j]);
                } else if ((board[i][j] == '1' && board[i][j-1] == '1')){
                    unionFind1.union(dots[i][j], dots[i][j-1]);
                }
            }
        }
        return unionFind1.sets();

    }

    public static class Dot{}

    public static class Node<V> {
        V value;
        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind1<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind1(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur :values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;

        }

        public void union(V a, V b) {
            Node<V> ahead = findFather(nodes.get(a));
            Node<V> bhead = findFather(nodes.get(b));
            if (ahead != bhead) {
                int aSize = sizeMap.get(ahead);
                int bSize = sizeMap.get(bhead);
                Node<V> big = aSize >= bSize ? ahead : bhead;
                Node<V> small = big == bhead ? ahead : bhead;
                parents.put(small, big);
                sizeMap.put(big, aSize + bSize);
                sizeMap.remove(small);
            }
        }

        public int sets() {
            return sizeMap.size();
        }
    }


    // ----------------------------------------------------------

    public static int numIslands2(char[][] board) {
        int row  = board.length;;
        int col = board[0].length;
        UnionFind2 uf = new UnionFind2(board);
        for (int j = 1; j < col; j++) {
            if (board[0][j-1] == '1' && board[0][j] == '1') {
                uf.union(0, j-1, 0, j);
            }
        }
        for (int i = 1; i < row ; i++) {
            if (board[i][0] == '1' && board[i-1][0] == '1') {
                uf.union(i, 0, i-1, 0);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == '1') {
                    if (board[i][j - 1] == '1') {
                        uf.union(i, j - 1, i, j);
                    }
                    if (board[i - 1][j] == '1') {
                        uf.union(i - 1, j, i, j);
                    }
                }
            }
        }
        return uf.sets();
    }


    public static class UnionFind2 {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int col;
        private int sets;

        public UnionFind2(char[][] board) {
            col = board[0].length;
            sets = 0;
            int row = board.length;;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
            for(int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (board[r][c] == '1') {
                        int i = index(r, c);
                        parent[i] = i;
                        size[i] = 1;
                        sets++;
                    }
                }
            }
        }

        private int index(int r, int c) {
            return r * col + c;
        }

        private int find(int i) {
            int hi = 0;
            while(i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(int r1, int c1, int r2, int c2) {
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }
}
