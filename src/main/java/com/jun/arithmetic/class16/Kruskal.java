package com.jun.arithmetic.class16;

import java.util.*;

/**
 * @className: Kruskal
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/9/16 08:39
 **/
public class Kruskal {

    public static class UnionFind {
        private HashMap<Node, Node> fatherMap;
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Node findFather(Node n) {
            Stack<Node> path = new Stack<>();
            while (n != fatherMap.get(n)) {
                path.add(n);
                n = fatherMap.get(n);
            }
            while(!path.isEmpty()) {
                fatherMap.put(path.pop(), n);
            }
            return n;
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aBai = findFather(a);
            Node bBai = findFather(b);
            if (aBai != bBai) {
                Integer aSize = sizeMap.get(a);
                Integer bSize = sizeMap.get(b);
                if (aSize <= bSize) {
                    fatherMap.put(a, bBai);
                    sizeMap.put(bBai, bSize + aSize);
                    sizeMap.remove(aBai);
                } else {
                    fatherMap.put(b, aBai);
                    sizeMap.put(aBai, aSize + bSize);
                    sizeMap.remove(bBai);
                }
            }
        }

    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge e1, Edge e2) {
            return e1.weight -  e2.weight;
        }
    }


    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());

    }




}
