package com.jun.arithmetic;

import lombok.extern.slf4j.Slf4j;

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

    public List<List<Customer>> unOptimizeTopK(int[] arr, boolean[] op, int k) {


    }




}
