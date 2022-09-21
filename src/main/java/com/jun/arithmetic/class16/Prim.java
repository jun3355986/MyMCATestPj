package com.jun.arithmetic.class16;

import java.util.*;

/**
 * @className: Prim
 * @description: 最小生成树
 * @author: jdt
 * @date: 2022/9/20 08:47
 **/
public class Prim {

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge e1, Edge e2) {
            return e1.weight - e2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph) {

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        HashSet<Node> nodesSet = new HashSet<>();

        Set<Edge> result = new HashSet<>();

        for (Node node : graph.nodes.values()) {
            if (!nodesSet.contains(node)) {
                nodesSet.add(node);
                priorityQueue.addAll(node.edges);
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll();
                    Node n = edge.to;
                    if (!nodesSet.contains(n)) {
                        result.add(edge);
                        nodesSet.add(n);
                        priorityQueue.addAll(n.edges);
                    }
                }
            }
        }

        return result;

    }

    public static int prim(int[][] graph) {

        int size = graph.length;
        int[] distances = new int[size];
        boolean[] visit = new boolean[size];
        visit[0] = true;
        for (int i = 0; i < size; i++) {
            distances[i] = graph[0][i];
        }

        int sum = 0;
        for(int i = 1; i < size; i++) {
            // 判断每次到各个点距离最近的点是哪个(遍历distance)，并找到最近的那条线，距离是多少
            int minPath = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < size ; j++ ) {
                // 被访问过的点不用处理
                if (!(visit[j]) && distances[j] < minIndex) {
                    minPath = distances[j];
                    minIndex = j;
                }
            }
            // 防止森林
            if (minIndex == -1) {
                return sum;
            }
            visit[minIndex] = true;
            sum += minPath;
            for (int j =0 ; j < size; j++) {
                // 被访问过的点不处理（因为不需要比较），选择前一个集合最小的边对应的点，并判断这个点的nexts到各个点的边是否小于现有的，是就选择，不是就跳过
                if (!(visit[j]) && distances[j] < graph[minIndex][j] ) {
                    distances[j] = graph[minIndex][j];
                }
            }
        }


        return sum;

    }

}
