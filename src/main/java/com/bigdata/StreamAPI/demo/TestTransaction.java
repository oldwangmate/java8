package com.bigdata.StreamAPI.demo;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestTransaction {

    List<Transaction> transactions;
    @Before
    public void before(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }
    //找出2011年发生的所有交易，并按交易额排序（从低到高）。
    @Test
    public void test(){
        transactions.stream()
                .filter((t) -> t.getYear() == 2011)
                .sorted((t1,t2) -> Integer.compare(t1.getValue(),t2.getValue()))
                .forEach(System.out::println);
    }
    //交易员都在哪些不同的城市工作过？
    @Test
    public void test1(){
       transactions.stream()
               .map((t) -> t.getTrader().getCity())
               .distinct()
               .forEach(System.out::println);
    }
    //查找所有来自于剑桥的交易员，并按姓名排序。
    @Test
    public void test2(){
        transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .sorted((t1,t2) -> t1.getName().compareTo(t2.getName()))
                .distinct()
                .forEach(System.out::println);
    }
    //返回所有交易员的姓名字符串，按字母顺序排序。
    @Test
    public void test3(){
        transactions.stream()
                .map((t) -> t.getTrader().getName())
                .sorted()
                .forEach(System.out::println);
        System.out.println("--------------------");

        String reduce = transactions.stream()
                .map((t) -> t.getTrader().getName())
                .sorted()
                .reduce("", String::concat);
        System.out.println(reduce);
        System.out.println("---------------------");

        transactions.stream()
                .map((t) -> t.getTrader().getName())
                .flatMap(TestTransaction::filterCharacter)
                .sorted((s1,s2) -> s1.compareToIgnoreCase(s2))
                .forEach(System.out::print);
        System.out.println();
        System.out.println("----------------------");

        String collect = transactions.stream()
                .map((t) -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(","));
        System.out.println(collect);
    }
    public static Stream<String> filterCharacter(String string){
        List<String> list = new ArrayList<>();
        for(Character character : string.toCharArray()){
            list.add(character.toString());
        }
        return list.stream();
    }
    //有没有交易员是在米兰工作的？
    @Test
    public void test4(){
        boolean milan = transactions.stream()
                .anyMatch((t) -> t.getTrader().getCity().equals("Milan"));
        System.out.println(milan);
    }
    //打印生活在剑桥的交易员的所有交易额
    @Test
    public void test5(){
        Optional<Integer> cambridge = transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);
        System.out.println(cambridge.get());

        System.out.println("---------------");

        IntSummaryStatistics cambridge1 = transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("Cambridge"))
                .collect(Collectors.summarizingInt(Transaction::getValue));
        System.out.println(cambridge1.getSum());
    }
    //所有交易中，最高的交易额是多少？
    @Test
    public void test6(){
        Optional<Integer> max = transactions.stream()
                .map((t) -> t.getValue())
                .max(Integer::compare);
        System.out.println(max.get());

        System.out.println("-------------");

        Optional<Integer> collect = transactions.stream()
                .map((t) -> t.getValue())
                .collect(Collectors.maxBy((t1, t2) -> Integer.compare(t1, t2)));
        System.out.println(collect.get());
    }
    //找到交易额最小的交易。
    @Test
    public void test7(){
        Optional<Transaction> min = transactions.stream()
                .min((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()));
        System.out.println(min);

        System.out.println("----------");

        Optional<Transaction> reduce = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        System.out.println(reduce);
    }

}
