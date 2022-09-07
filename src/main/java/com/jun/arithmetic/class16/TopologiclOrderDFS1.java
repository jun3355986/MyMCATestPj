package com.jun.arithmetic.class16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: TopologicDFS1
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/9/7 09:12
 **/
public class TopologiclOrderDFS1 {

    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static class Record {
        public DirectedGraphNode node;
        public int deep;

        public Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    public static class MyComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            return o1.deep - o2.deep;
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graphNodes) {
        Map<DirectedGraphNode, Record> recordMap = new HashMap<>(graphNodes.size());
        for (DirectedGraphNode node : graphNodes) {
            f (node, recordMap);
        }
        ArrayList<Record> recordArr = new ArrayList<>(recordMap.values() );
        recordArr.sort(new MyComparator());
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record record : recordArr) {
            ans.add(record.node);
        }
        return ans;
    }

    public static Record f(DirectedGraphNode node, Map<DirectedGraphNode, Record> order) {
        if (order.containsKey(node)) {
            return order.get(node);
        }
        int follow = 0;
        for (DirectedGraphNode next : node.neighbors) {
            follow = Math.max(follow, f(next, order).deep);
        }
        Record ans = new Record(node, follow + 1);
        order.put(node, ans);
        return ans;
    }

}
