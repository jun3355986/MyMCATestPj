package com.jun.arithmetic.class13;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: Code04_MaxHappy
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/7/16 13:00
 **/
public class Code04_MaxHappy {

    public static class Employee {
        public int happy;
        public List<Employee> nexts;
        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }
    }

    public static int maxHappy1(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return process1(boss, false);
    }

    public static int process1(Employee cur, boolean up) {
        if (up) {
            int ans = 0;
            for (Employee next : cur.nexts) {
                ans += process1(next, false);
            }
            return ans;
        } else {
            // 当前employee来
            int p1 = cur.happy;
            // 当前employee不来
            int p2 = 0;
            for (Employee next : cur.nexts) {
                // 当前employee来，下面的最大快乐值
                p1 += process1(next, true);
                // 当前employee来
                p2 += process1(next, false);
            }
            return Math.max(p1, p2);
        }
    }

    public static int maxHappy2(Employee boss) {
        Info allInfo = process(boss);
        return Math.max(allInfo.no, allInfo.yes);
    }

    public static class Info {
        public int no;
        public int yes;
        public Info(int n, int y) {
            no = n;
            yes = y;
        }
    }

    public static Info process(Employee employee) {
        if (employee == null){
            return new Info(0, 0);
        }
        int no = 0;
        int yes = employee.happy;
        for (Employee next : employee.nexts) {
            Info nextInfo = process(next);
            yes += nextInfo.no;
            no += Math.max(nextInfo.no , nextInfo.yes);
        }
        return new Info(no, yes);
    }

    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != maxHappy2(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }



}
