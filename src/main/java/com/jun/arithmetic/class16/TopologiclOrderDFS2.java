package com.jun.arithmetic.class16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: TopologicOrderDFS2
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/9/8 08:46
 **/
public class TopologiclOrderDFS2 {

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
        public long nodes;

        public Record(DirectedGraphNode node, long deep) {
            this.node = node;
            this.nodes = nodes;
        }
    }

    public static class MyComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            return Long.compare(o2.nodes, o1.nodes);
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        if (graph == null) {
            return null;
        }
        HashMap<DirectedGraphNode, Record> record = new HashMap<>(graph.size());
        for(DirectedGraphNode node : graph) {
            f(node, record);
        }
        ArrayList<Record> orderList = new ArrayList<>();
        for (Record node : record.values()) {
            orderList.add(node);
        }
        orderList.sort(new MyComparator());
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record record1 : orderList) {
            ans.add(record1.node);
        }
        return ans;

    }

    public static Record f(DirectedGraphNode node, Map<DirectedGraphNode, Record> recordMap) {
        if (recordMap.containsKey(node)) {
            return recordMap.get(node);
        }
        long nodes = 0;
        for (DirectedGraphNode node1 : node.neighbors ) {
            nodes += f(node1, recordMap).nodes;
        }
        Record record = new Record(node, nodes + 1);
        recordMap.put(node, record);
        return record;
    }
}
