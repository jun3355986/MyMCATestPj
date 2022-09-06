package com.jun.arithmetic.class16;

import java.util.HashSet;
import java.util.Stack;

/**
 * @className: DFS
 * @description: 图的深度优先遍历
 * @author: jdt
 * @date: 2022/9/6 08:59
 **/
public class DFS {

    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.ou

    }

}
