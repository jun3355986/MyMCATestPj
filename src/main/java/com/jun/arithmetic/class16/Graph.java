package com.jun.arithmetic.class16;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @className: Graph
 * @description: 图
 * @author: jdt
 * @date: 2022/9/6 08:36
 **/
public class Graph {

    /**
     * 图上点的数值，对应封闭的点
     */
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
