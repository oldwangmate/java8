package com.bigdata.StreamAPI;

import com.bigdata.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream的中间操作
 */
public class TestStreamAPI2 {

    /**
     *  筛选与切片
     *  filter 接受lambda,从流中排除某些元素
     *  limit 截断流 使其元素不超过给定数量
     *  skip(N) 跳过元素，返回一个扔掉了前N个元素的流 若流中的元素不足N个 则返回一个空流 与limit(N)互补
     *  distinct 筛选 通过流生成元素的HashCode() 和equals() 去除重复元素
     *  map--- 接受lambda 将元素抓换成其他形式或提取信息，接受一个函数作为参数，该函数会应用到每个元素上，并将其映射成为一个新的元素
     *  flatMap --- 接受一个函数作为参数，将流中的每一个值都换成另外一个流，冉后把所有的流连城一个流
     *  sorted() -- 自然排序----(comparable)
     *  sorted(Comparator com) 定制排序---(Comparator)
     */
    List<Employee> employees = Arrays.asList(
            new Employee("张三",18,999),
            new Employee("李四",18,9990),
            new Employee("王五",18,999),
            new Employee("赵六",18,999),
            new Employee("田七",36,5099)
    );

    /**
     * 排序
     *  sorted() -- 自然排序----(comparable)
     *  sorted(Comparator com) 定制排序---(Comparator)
     */
    @Test
    public void test6(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("------------");
        employees.stream()
               .sorted((e1,e2) -> {
                   if(e1.getName().equals(e2.getName())){
                       return Integer.compare(e1.getAge(),e2.getAge());
                   }else {
                       return Integer.compare(e1.getAge(),e2.getAge());
                   }
               }).forEach(System.out::println);
    }

    /**
     * 映射
     *  map--- 接受lambda 将元素抓换成其他形式或提取信息，接受一个函数作为参数，该函数会应用到每个元素上，并将其映射成为一个新的元素
     *  flatMap --- 接受一个函数作为参数，将流中的每一个值都换成另外一个流，冉后把所有的流连城一个流
     */
    @Test
    public void test5(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
        list.stream()
                .map((s -> s.toUpperCase()))
                .forEach(System.out::println);
        System.out.println("------------------");
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
        System.out.println("------------------");
        Stream<Stream<Character>> stream = list.stream()
                .map(TestStreamAPI2::filterCharcater);
        stream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });
        System.out.println("-----------------");
        list.stream()
                .flatMap(TestStreamAPI2::filterCharcater)
                .forEach(System.out::println);

    }

    public static Stream<Character> filterCharcater(String string){
        List<Character> list = new ArrayList<>();
        for(Character character : string.toCharArray()){
            list.add(character);
        }
        return list.stream();
    }



    //内部迭代，迭代操作由StreamAPI完成
    @Test
    public void test(){
        //中间操作：不会执行任何操作
        List<Employee> employees = Arrays.asList(
                new Employee("张三",18,999),
                new Employee("李四",18,9990),
                new Employee("王五",18,999),
                new Employee("赵六",18,999),
                new Employee("田七",36,5099),
                new Employee("田七",36,5099),
                new Employee("田七",36,5099),
                new Employee("田七",36,5099),
                new Employee("田七",36,5099)
        );
        Stream<Employee> stream = employees.stream();
        Stream<Employee> stream1 = stream.filter((x) -> x.getAge() > 35);

        //终止操作：一次性执行全部内容，即惰性操作
        stream1.forEach(System.out::println);
    }

    @Test
    public void test1(){
        employees.stream().filter((e) -> e.getSalary() > 5000)
                .limit(2)
                .forEach(System.out::println);
    }
    @Test
    public void test3(){
        employees.stream()
                .filter((e) -> e.getSalary() > 500)
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void test4(){
        employees.stream()
                .filter((e) -> e.getSalary() > 500)
                .distinct()
                .forEach(System.out::println);
    }

}
