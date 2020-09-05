package com.bigdata.StreamAPI.forkJoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.OptionalLong;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class TestForJoin {

    /**
     * java 8 并行流
     */
    @Test
    public void test3(){
        Instant start = Instant.now();
        long reduce = LongStream.rangeClosed(0, 100000000L)
                .parallel() //并行流
//                        .sequential()// 顺序流
                .reduce(0, Long::sum);
        System.out.println(reduce);
        Instant end = Instant.now();
        System.out.println("耗时："+Duration.between(start,end).toMillis());
    }

    /**
     * ForkJoin 框架
     */
    @Test
    public void test(){
        Instant start = Instant.now();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task= new ForkJoinCalculate(0L, 100000000L);
        Long invoke = forkJoinPool.invoke(task);
        System.out.println(invoke);
        Instant end = Instant.now();
        System.out.println("耗时："+Duration.between(start,end).toMillis());
    }

    /**
     * 普通 for
     */
    @Test
    public void test2(){
        Instant start = Instant.now();
        long sum = 0L;
        for(long i = 0 ; i <=100000000;i++ ){
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗时："+Duration.between(start,end).toMillis());
    }
}
