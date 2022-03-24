package com.jun.common.innerclass;

import lombok.extern.slf4j.Slf4j;

/**
 * @className: Outer
 * @description: 内部类的访问范围测试
 * @author: jdt
 * @date: 2022/3/23 00:00
 **/
@Slf4j
public class Outer {

    private int outP = 3;
    private class Inner {
        private int inP = 5;
        public void accessOuterP() {
            log.info("访问外部类成员：{}",  outP);
        }
    }

    public class Inner3 {
        private int inP = 5;
        public void accessOtherInner() {
            log.info("访问外部类成员：{}",  new Inner().inP);
        }
    }

    public static class Inner2{
        public void accessOuter() {
            log.info("aaaa: {}", new Outer().outP);
        }
    }

    public void accessInP() {
        log.info("访问内部类的private成员: {}", new Inner().inP);
    }
    public static void hk() {
        new Outer().accessInP();
    }
    public static void main(String[] args) {

        Outer outer = new Outer();
        outer.accessInP();
        Outer.hk();

    }

}
