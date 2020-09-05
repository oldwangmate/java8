package com.bigdata.api_newdate;

import org.junit.Test;

import java.time.*;
import java.util.concurrent.TimeUnit;

/**
 * 日期时间操作
 */
public class TestLocalDateTime {

    //4.Period计算两个日期之间的间隔

    @Test
    public void test4(){
        LocalDate localDate1 = LocalDate.of(2020,9,1);
        LocalDate localDate2 = LocalDate.now();
        Period period = Period.between(localDate1, localDate2);
        System.out.println(period);

        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }
    // 3.Duration 计算两个时间之间的间隔
    @Test
    public void test3() throws InterruptedException {
        Instant instant1 = Instant.now();
        TimeUnit.SECONDS.sleep(1);
        Instant instant2 = Instant.now();
        Duration between = Duration.between(instant1, instant2);
        System.out.println(between.toMillis());

        System.out.println("----------------");

        LocalTime localTime1 = LocalTime.now();
        TimeUnit.SECONDS.sleep(1);
        LocalTime localTime2 = LocalTime.now();
        System.out.println(Duration.between(localTime1,localTime2).toMillis());
    }


    // 2.Instant 时间戳（Unix 元年 1970年一月一日零时零分零秒到此时的毫秒值）
    @Test
    public void test2(){
        Instant now = Instant.now();  //默认获取的是UTC时区 UTC世界协调时间
        System.out.println(now);

        OffsetDateTime time = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(time);

        System.out.println(now.toEpochMilli());  //转成毫秒值 时间戳

        Instant instant = Instant.ofEpochSecond(60);
        System.out.println(instant);
    }

    // 1. LocalDate表示日期  LocalTime表示时间 LocalDateTime表示日期时间
    @Test
    public void test1(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        LocalDateTime localDateTime1 = LocalDateTime.of(2015, 10, 19, 13, 22, 33);
        System.out.println(localDateTime1);

        LocalDateTime localDateTime2 = localDateTime.plusYears(1);  //加一年
        System.out.println(localDateTime2);

        LocalDateTime localDateTime3 = localDateTime.minusMonths(2); //减2月
        System.out.println(localDateTime3);

        System.out.println(localDateTime.getYear());  //获取年
        System.out.println(localDateTime.getMonthValue()); // 获取月
        System.out.println(localDateTime.getDayOfMonth()); //获取天
        System.out.println(localDateTime.getHour()); //小时
        System.out.println(localDateTime.getMinute()); //分钟
        System.out.println(localDateTime.getSecond()); //秒
        System.out.println(localDateTime.getMonth()); //英语月份
    }
}
