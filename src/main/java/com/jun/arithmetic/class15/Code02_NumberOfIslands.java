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
}
