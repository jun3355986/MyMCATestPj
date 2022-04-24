package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @decription: 每一步操作返回得奖用户
 * @date: 2022/4/20 17:17
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@Slf4j
public class EveryStepShowBoss {

    static class Customer{
        public int id;
        public int buy;
        public int enterTime;
        public Customer(int v , int b, int o) {
            id = v;
            buy = b;
            enterTime = o;
        }
    }

    public List<List<Integer>> unOptimizeTopK(int[] arr, boolean[] op, int k) {

        HashMap<Integer, Customer> map = new HashMap<>();
        List<Customer> cands = new ArrayList<>();
        List<Customer> daddy = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();

        for(int i = 0 ; i < arr.length ; i++) {
            int id = arr[i];
            boolean buyOrRefund = op[i];
            if (!map.containsKey(id) && !buyOrRefund) {
                ans.add(getCurAns(daddy));
                continue;
            }
            if (!map.containsKey(id)) {
                map.put(id, new Customer(id, 0, 0));
            }
            Customer c = map.get(id);
            if (buyOrRefund) {
                c.buy++;
            } else {
                c.buy--;
            }
            if(c.buy == 0) {
                map.remove(id);
            }

            if (!cands.contains(c) && !daddy.contains(c)) {
                if (daddy.size() < k) {
                    daddy.add(c);
                } else {
                    cands.add(c);
                }
            }
            cleanZeroBuy(cands);
            cleanZeroBuy(daddy);
            cands.sort(new CandidateComparator());
            daddy.sort(new DaddyComparator());
            move(cands, daddy, k, i);
            ans.add(getCurAns(daddy));
        }
        return ans;
    }

    public List<Integer> getCurAns(List<Customer> daddy) {
        List<Integer> ans = new ArrayList<>();
        for (Customer c : daddy) {
            ans.add(c.id);
        }
        return ans;
    }

    static class CandidateComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer a, Customer b) {
            return (a.buy != b.buy) ? b.buy - a.buy : a.enterTime - b.enterTime;
        }
    }

    static class DaddyComparator implements Comparator<Customer>{
        @Override
        public int compare(Customer a, Customer b) {
            return (a.buy != b.buy) ? a.buy - b.buy : a.enterTime - b.enterTime;
        }
    }

    private void cleanZeroBuy(List<Customer> list) {
        List<Customer> noZero = new ArrayList<>();
        for (Customer c : list) {
            if (c.buy != 0) {
                noZero.add(c);
            }
        }
        list.clear();
        list.addAll(noZero);
    }

    private void move(List<Customer> cands, List<Customer> daddy, int k, int time) {
        if (cands.isEmpty()) {
            return;
        }
        if (daddy.size() < k ) {
            Customer c = cands.get(0);
            c.enterTime = time;
            daddy.add(c);
            cands.remove(c);
        } else {
            if (cands.get(0).buy > daddy.get(0 ).buy) {
                Customer oldDaddy = daddy.get(0);
                daddy.remove(0);
                Customer newDaddy = cands.get(0);
                cands.remove(0);
                oldDaddy.enterTime = time;
                newDaddy.enterTime = time;
                daddy.add(newDaddy);
                cands.add(oldDaddy);

            }
        }
    }


    /**
     * 优化后的版本
     * @param arr
     * @param op
     * @param k
     */
    public List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        HashMap<Integer, Customer> customers = new HashMap<>();
        GreaterHeap<Customer> candHeap = new GreaterHeap<>(new CandidateComparator());
        GreaterHeap<Customer> daddyHeap = new GreaterHeap<>(new DaddyComparator());
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i< arr.length; i++) {

            boolean buyOrRefund = op[i];
            int id = arr[i];
            if (!buyOrRefund && !customers.containsKey(id)) {
                ans.add(getCurAns(daddyHeap.getAllElements()));
                continue;
            }
            if (!customers.containsKey(id)) {
                customers.put(id, new Customer(id, 0, 0));
            }
            Customer c = customers.get(id);
            if (buyOrRefund) {
                c.buy++;
            } else {
                c.buy--;
            }
            if(c.buy == 0) {
                customers.remove(id);
            }
            if (!candHeap.contains(c) && !daddyHeap.contains(c)) {
                if (daddyHeap.size() < k) {
                    c.enterTime = i;
                    daddyHeap.push(c);
                } else {
                    c.enterTime = i;
                    candHeap.push(c);
                }
            } else if (candHeap.contains(c)) {
                if (c.buy == 0) {
                    candHeap.remove(c);
                } else {
                    candHeap.resign(c);
                }
            } else {
                if (c.buy == 0) {
                    daddyHeap.remove(c);
                } else {
                    daddyHeap.resign(c);
                }
            }
            daddyMove(candHeap, daddyHeap, k, i);
            ans.add(getCurAns(daddyHeap.getAllElements()));
        }
        return ans;
    }

    public void daddyMove(GreaterHeap<Customer> candHeap, GreaterHeap<Customer> daddyHeap, int k, int time) {
        if (candHeap.isEmpty()) {
            return;
        }
        if (daddyHeap.size() < k) {
            Customer c = candHeap.pop();
            c.enterTime = time;
            daddyHeap.push(c);
        } else {
            if (candHeap.peek().buy > daddyHeap.peek().buy) {
                Customer oldDaddy = daddyHeap.pop();
                Customer newDaddy = candHeap.pop();
                oldDaddy.enterTime = time;
                newDaddy.enterTime = time;
                daddyHeap.push(newDaddy);
                candHeap.push(oldDaddy);
            }
        }
    }


    // 为了测试
    public static class Data {
        public int[] arr;
        public boolean[] op;

        public Data(int[] a, boolean[] o) {
            arr = a;
            op = o;
        }
    }

    // 为了测试
    public static Data randomData(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        boolean[] op = new boolean[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
            op[i] = Math.random() < 0.5 ? true : false;
        }
        return new Data(arr, op);
    }

    // 为了测试
    public static boolean sameAnswer(List<List<Integer>> ans1, List<List<Integer>> ans2) {
        if (ans1.size() != ans2.size()) {
            return false;
        }
        for (int i = 0; i < ans1.size(); i++) {
            List<Integer> cur1 = ans1.get(i);
            List<Integer> cur2 = ans2.get(i);
            if (cur1.size() != cur2.size()) {
                log.info("出错位置：{}, cur1: {}, cur2: {}", i, cur1, cur2);
                return false;
            }
            cur1.sort((a, b) -> a - b);
            cur2.sort((a, b) -> a - b);
            for (int j = 0; j < cur1.size(); j++) {
                if (!cur1.get(j).equals(cur2.get(j))) {
                    log.info("出错位置：{}, cur1: {}, cur2: {}", i, cur1, cur2);
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int maxValue = 10;
        int maxLen = 100;
        int maxK = 6;
        int testTimes = 100000;
        EveryStepShowBoss essb1 = new EveryStepShowBoss();
        EveryStepShowBoss essb2 = new EveryStepShowBoss();
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Data testData = randomData(maxValue, maxLen);
            int k = (int) (Math.random() * maxK) + 1;
            int[] arr = testData.arr;
            boolean[] op = testData.op;
            List<List<Integer>> ans1 = essb1.topK(arr, op, k);
            List<List<Integer>> ans2 = essb2.unOptimizeTopK(arr, op, k);
            if (!sameAnswer(ans1, ans2)) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.println("step:" + j + ": " +arr[j] + " , " + op[j]);
                }
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }



}
