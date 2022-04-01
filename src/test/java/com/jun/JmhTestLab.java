package com.jun;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @decription: 使用jmh工具的测试
 * @date: 2022/4/1 16:38
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JmhTestLab {

    @Benchmark
    public boolean testFind() {
        return false;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JmhTestLab.class.getSimpleName())
                .threads(2)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
