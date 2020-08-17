package com.bigdata.lambda;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

public class Testlambda {


    //原来的匿名内部类
    @Test
    public void test1(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
        treeSet.add(3);
        treeSet.add(2);
        for (Integer integer : treeSet) {
            System.out.println(integer);
        }
    }

    //lambda
    @Test
    public void test2(){
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    //获取当前公司中员工年龄大于35的员工信息
    @Test
    public void test3(){
        List<Employee> employees = filterEmployee(this.employees);
        employees.forEach(e -> {
            System.out.println(e);
        });

    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三",18,999),
            new Employee("李四",18,9990),
            new Employee("王五",18,999),
            new Employee("赵六",18,999),
            new Employee("田七",36,5099)
    );

     public List<Employee> filterEmployee(List<Employee> list){
        List<Employee> emps = new ArrayList<>();
         for (Employee employee : list) {
             if(employee.getAge() >= 36){
                 emps.add(employee);
             }
         }
         return emps;
     }

    public List<Employee> filterEmployees(List<Employee> list,MyPredicate<Employee> myPredicate){
      List<Employee> employees = new ArrayList<>();
        for (Employee employee : list) {
            if(myPredicate.test(employee)){
                employees.add(employee);
            }
        }
        return employees;
    }

    @Test
    public void test4(){
        List<Employee> employees = Arrays.asList(
                new Employee("张三",18,999),
                new Employee("李四",18,9990),
                new Employee("王五",18,999),
                new Employee("赵六",18,999),
                new Employee("田七",36,5099)
        );
        Stream<Employee> stream = employees.stream();
        stream.filter(employee -> employee.getAge() > 20).forEach(employee -> System.out.println(employee));
    }

    //lambda 表达式
    @Test
    public void test5(){
        List<Employee> employeess = filterEmployees(this.employees, (e) -> e.getAge() > 35);
        employeess.forEach(System.out::println);
        System.out.println("---------------------------");
        employees.stream().map(Employee::getAge).forEach(System.out::println);

    }

}
