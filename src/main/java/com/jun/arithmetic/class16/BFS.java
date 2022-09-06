package com.jun.arithmetic.class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @className: BFS
 * @description: 图的宽度优先遍历
 * @author: jdt
 * @date: 2022/9/6 08:42
 **/
public class BFS {

    public static void bfsf(Node start) {
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.value);
            for(Node next : node.nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }
}
