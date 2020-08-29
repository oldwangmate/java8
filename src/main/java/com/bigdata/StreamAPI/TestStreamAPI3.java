package com.bigdata.StreamAPI;

import com.bigdata.lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStreamAPI3 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三",18,999,Employee.Status.FREE),
            new Employee("李四",18,9990 ,Employee.Status.BUSY),
            new Employee("王五",18,999,Employee.Status.VOCATION),
            new Employee("赵六",18,999,Employee.Status.FREE),
            new Employee("田七",36,5099,Employee.Status.BUSY),
            new Employee("田七",36,5099,Employee.Status.BUSY)
    );
    /*
       查找与匹配
       allMath  检查是否匹配所有元素
       anyMath  检查是否至少匹配一个元素
       noneMath 检查是否没有匹配所有元素
       findFirst 返回第一个元素
       findAny  返回当前流中的任意元素
       count 返回流中元素最大的总个数
       max 返回流中最大值
       min 返回流中最小值

       归约 reduce(T identity BinaryOperator) / reduce(BinaryOperator) 可以将流中元素反复结合起来，得到一个值
       收集 collect 将流转换为其他形式，接受一个collector接口的实现，用于给Stream中元素做汇总的方法
           collect(Collectors.xxx()) 核心工具类 需要研究

     */
    @Test
    public void test10(){
        String collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));
        System.out.println(collect);
    }

    @Test
    public void test9(){
        DoubleSummaryStatistics collect = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect);
    }

    //分区
    @Test
    public void test8(){
        Map<Boolean, List<Employee>> collect = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));
        System.out.println(collect);
    }


    //多级分组
    @Test
    public void test7(){
        Map<Employee.Status, Map<String, List<Employee>>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (((Employee) e).getAge() <= 18) {
                        return "晨阳";
                    } else {
                        return "太阳";
                    }
                })));
        System.out.println(collect);
    }

    //分组
    @Test
    public void test6(){
        Map<Employee.Status, List<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        Set<Map.Entry<Employee.Status, List<Employee>>> entries = collect.entrySet();
        entries.forEach(System.out::println);

    }

    @Test
    public void test5(){
        //总数
        Long collect = employees.stream()
                .collect(Collectors.counting());
        System.out.println(collect);

        // 平均值
        Double collect1 = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect1);

        //工资总和
        Double collect2 = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(collect2);

        //最大值
        Optional<Employee> collect3 = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(collect3);
        //最小值
        Optional<Double> collect4 = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(collect4);
    }

    @Test
    public void test4(){
        List<String> collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);

        Set<String> set = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);

        HashSet<String> collect1 = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(collect1);
    }

    @Test
    public void test3(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer reduce = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        System.out.println("---------------");

        Optional<Double> reduce1 = employees.stream().map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(reduce1.get());

    }

    @Test
    public void test1(){
        boolean match = employees.stream()
                .allMatch((employee -> employee.getStatus().equals(Employee.Status.BUSY)));
        System.out.println(match);

        boolean match1 = employees.stream()
               .anyMatch((employee) -> employee.getStatus().equals(Employee.Status.BUSY));
        System.out.println(match1);

        boolean match2 = employees.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(match2);

        Optional<Employee> first = employees.stream()
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                .findFirst();
        System.out.println(first);

        Optional<Employee> any = employees.parallelStream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(any);

    }
    @Test
    public void test2(){
        long count = employees.stream().count();
        System.out.println(count);

        Optional<Employee> max = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getAge(), e2.getAge()));
        System.out.println(max);

        Optional<Double> min = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(min.get());
    }
}
