package com.bigdata.DateThreadSecurity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class DataFormatThreadLocal {

    private static  final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Callable<Date> callable = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return threadLocal.get().parse("20200905");
            }
        };

        List<Future<Date>> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(executorService.submit(callable));
        }
        for (Future<Date> dateFuture : list) {
            System.out.println(dateFuture.get());
        }

        executorService.shutdown();
    }
}
