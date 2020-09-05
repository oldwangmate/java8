package com.bigdata.api_newdate;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class TestTemporalAdjuster {
    //5.TemporalAdjuster 时间校正器
    @Test
    public void test1(){
        LocalDateTime localDateTime1 = LocalDateTime.now();
        System.out.println(localDateTime1);
        LocalDateTime localDateTime2 = localDateTime1.withDayOfMonth(10);
        System.out.println(localDateTime2);

        LocalDateTime with = localDateTime1.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)); //下一个周日
        System.out.println(with);

        //自定义：下一个工作日
        LocalDateTime with1 = localDateTime1.with((date) -> {
            LocalDateTime localDateTime = (LocalDateTime) date;
            DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return localDateTime.plusDays(3);
            } else if(dayOfWeek.equals(DayOfWeek.SATURDAY)){
                return localDateTime.plusDays(2);
            }else {
                return localDateTime.plusDays(1);
            }
        });
        System.out.println(with1);
    }
}
