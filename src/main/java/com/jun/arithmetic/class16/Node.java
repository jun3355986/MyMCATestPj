package com.jun.arithmetic.class16;

import java.util.ArrayList;

/**
 * @className: Node
 * @description: 点结构
 * @author: jdt
 * @date: 2022/9/6 08:31
 **/
public class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
