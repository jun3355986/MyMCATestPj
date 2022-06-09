package com.jun.arithmetic;

/**
 * @className: Code07_PaperFolding
 * @description: 折纸算法
 * @author: jdt
 * @date: 2022/6/9 08:52
 **/
public class Code07_PaperFolding {


    public static void printAllFoldings(int N) {
        process(1, N, true);
    }

    public static void process(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        process(i + 1, N, true);
        System.out.print(down? "凹":"凸");
        process( i + 1, N, false);
    }

    public static void main(String[] args) {
        printAllFoldings(10);
    }
}
