package com.bigdata.StreamAPI.forkJoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;
    private static final long THRESHOLE = 10000;
    public ForkJoinCalculate(Long start,Long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
       Long length = end - start;
       if(length <= THRESHOLE){
           Long sum = 0L;
           for(Long i = start ;i <= end ;i ++){
               sum += i;
           }
           return sum;
       }else {
           long middle = (start + end) / 2;
           ForkJoinCalculate left = new ForkJoinCalculate(start,middle);
           left.fork();// 拆分子任务 同时压入线程队列

           ForkJoinCalculate right = new ForkJoinCalculate(middle+1,end);
           right.fork();

           return left.join() + right.join();
       }
    }


}
