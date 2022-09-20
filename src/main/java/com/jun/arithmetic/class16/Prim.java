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

}
