package com.jun.arithmetic.class16;

import java.util.*;

/**
 * @className: TopologySort
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/9/9 09:04
 **/
public class TopologySort {

    public static List<Node> sortedTopology(Graph graph) {
        HashMap<Node, Integer> inMap = new HashMap<>(graph.nodes.size());
        Queue<Node> zeroInQueue = new LinkedList<>();
        for(Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }

        ArrayList<Node> ans = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node node = zeroInQueue.poll();
            ans.add(node);
            for(Node next : node.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return ans;
    }

}
