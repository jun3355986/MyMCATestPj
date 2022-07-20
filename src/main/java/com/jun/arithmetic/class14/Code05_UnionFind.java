package com.jun.arithmetic.class14;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @className: Code05_UnionFind
 * @description: 并查集
 * @author: jdt
 * @date: 2022/7/20 23:22
 **/
public class Code05_UnionFind {

    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value: values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 把路径扁平化
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

        public boolean isSameSet(V a, V b) {
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(nodes.get(a));
                int bSetSize = sizeMap.get(nodes.get(b));
                Node<V> bigNode = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> smallNode = bigNode == aHead ? bHead : aHead;
                parents.put(smallNode, bigNode);
                sizeMap.remove(smallNode);
                sizeMap.put(bigNode, aSetSize + bSetSize);
            }
        }

        public int sets() {
            return sizeMap.size();
        }
    }
}
