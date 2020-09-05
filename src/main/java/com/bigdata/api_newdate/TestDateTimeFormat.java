package com.bigdata.api_newdate;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class TestDateTimeFormat {

    //7.ZonedDate 、ZonedTime ZonedDateTime 带时区
    @Test
    public void test2(){
        Set<String> zoneIds = ZoneId.getAvailableZoneIds(); //获取所有时区
        zoneIds.forEach(System.out::println);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(now);

        LocalDateTime now1 = LocalDateTime.now();
        ZonedDateTime zonedDateTime = now1.atZone(ZoneId.of("Asia/Shanghai")); //带时区的日期格式
        System.out.println(zonedDateTime);
    }



    //6.DateTimeFormatter 格式化时间日期
    @Test
    public void test1(){
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ISO_DATE;
        LocalDateTime now = LocalDateTime.now();

        String format1 = now.format(dateTimeFormatter1);
        System.out.println(format1);

        System.out.println("-------------");

        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format2 = dateTimeFormatter2.format(now);
        System.out.println(format2);

        LocalDateTime parse = now.parse(format2, dateTimeFormatter2);
        System.out.println(parse);

    }
}
