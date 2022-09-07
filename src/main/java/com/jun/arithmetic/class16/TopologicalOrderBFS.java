package com.jun.arithmetic.class16;

import java.util.*;

/**
 * @className: TopologicalOrderBFS
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/9/7 08:31
 **/
public class TopologicalOrderBFS {

    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graphNodes) {

        HashMap<DirectedGraphNode, Integer> indegreeMap = new HashMap<>();
        for (DirectedGraphNode node : graphNodes){
            indegreeMap.put(node, 0);
        }

        for (DirectedGraphNode node : graphNodes) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                indegreeMap.put(neighbor, indegreeMap.get(neighbor) + 1);
            }
        }

        Queue<DirectedGraphNode> zeroIndegrees = new LinkedList<>();
        for (DirectedGraphNode node : indegreeMap.keySet()) {
            if (indegreeMap.get(node) == 0) {
                zeroIndegrees.add(node);
            }
        }

        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        while (!zeroIndegrees.isEmpty()) {
            DirectedGraphNode node = zeroIndegrees.poll();
            ans.add(node);
            for(DirectedGraphNode cur : node.neighbors) {
                indegreeMap.put(cur, indegreeMap.get(cur) - 1);
                if (indegreeMap.get(cur) == 0) {
                    zeroIndegrees.offer(cur);
                }
            }
        }
        return ans;

    }

}
