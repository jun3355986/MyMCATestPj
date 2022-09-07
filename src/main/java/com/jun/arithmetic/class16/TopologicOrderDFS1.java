package com.jun.arithmetic.class16;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @className: TopologicDFS1
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/9/7 09:12
 **/
public class TopologicOrderDFS1 {

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

    public

}
