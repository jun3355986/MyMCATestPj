package com.jun.arithmetic.class14;


import java.util.Arrays;
import java.util.Comparator;

/**
 * @className: Code03_BestArrange
 * @description: TODO 类描述
 * @author: jdt
 * @date: 2022/7/20 21:47
 **/
public class Code03_BestArrange {

    public static class Program {
        public int start;
        public int end;
        public Program(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static int bestArrange1(Program[] programs){
        if (programs == null || programs.length == 0) {
            return 0;
        }
        return process(programs, 0, 0);
    }

    public static int process(Program[] programs, int done, int timeLine) {
        if (programs.length == 0) {
            return done;
        }
        int max = done;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine) {
                max = Math.max(max, process( copyButExcept(programs, i), done + 1, programs[i].end));
            }
        }
        return max;
    }

    public static Program[] copyButExcept(Program[] programs, int i) {
        Program[] ans = new Program[programs.length - 1];
        int index = 0;
        for (int k = 0; k < programs.length; k++) {
            if (k != i) {
                ans[index++] = programs[k];
            }
        }
        return ans;
    }



    // 给出全部待安排的会议，看看能最多安排多少
    public static int bestArrange2(Program[] programs) {
        Arrays.sort(programs, new ProgramComparator());
        int result = 0;
        int timeLine = 0;
        for (Program program : programs) {
            if (program.start >= timeLine) {
                result++;
                timeLine = program.end;
            }
        }
        return result;
    }

    public static class ProgramComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    // for test
    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            if (bestArrange1(programs) != bestArrange2(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
