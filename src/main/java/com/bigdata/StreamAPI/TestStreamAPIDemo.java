package com.bigdata.StreamAPI;

import com.bigdata.lambda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TestStreamAPIDemo {
    /*
      给定一个数字列表，如何返回一个由每个数的平方构成的列表
      给定【1,2,3,4,5】 应该返回【1,4,9,16,25】
     */
    @Test
    public void test(){
        Integer[] nums = new Integer[]{1,2,3,4,5};
       Arrays.stream(nums)
               .map((e) -> e * e).forEach(System.out::println);
        System.out.println("-----------------");
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三",18,999,Employee.Status.FREE),
            new Employee("李四",18,9990 ,Employee.Status.BUSY),
            new Employee("王五",18,999,Employee.Status.VOCATION),
            new Employee("赵六",18,999,Employee.Status.FREE),
            new Employee("田七",36,5099,Employee.Status.BUSY),
            new Employee("田七",36,5099,Employee.Status.BUSY)
    );
    /*
     *2.用map和reduce方法数一数流中一共用多少个Employee
     */
    @Test
    public void test1(){
        Optional<Integer> reduce = employees.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);
        System.out.println(reduce.get());

    }
}
