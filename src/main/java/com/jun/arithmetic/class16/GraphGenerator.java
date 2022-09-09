package com.jun.arithmetic.class16;

/**
 * @className: GraphGenerator
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/9/9 08:13
 **/
public class GraphGenerator {


    public static Graph createGraph(int[][] matrix) {

        Graph graph = new Graph();
        for (int[] col : matrix) {

            int weight = col[0];
            int from = col[1];
            int to = col[2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)){
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);

        }
        return graph;

    }

}
