package com.jun.arithmetic.class16;

/**
 * @className: Edge
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/9/6 08:34
 **/
public class Edge {

    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
