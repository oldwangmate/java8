package com.bigdata.DateThreadSecurity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * java 8 新日期格式
 */
public class TestLocalDate {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> callable = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20200905",dateTimeFormatter);
            }
        };

        List<Future<LocalDate>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(pool.submit(callable));
        }
        for (Future<LocalDate> localDateFuture : list) {
            System.out.println(localDateFuture.get(3,TimeUnit.SECONDS));
        }
        pool.shutdown();
    }
}
