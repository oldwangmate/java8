package com.bigdata.LambdaInterfase;

import com.bigdata.lambda.Employee;
import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 方法引用：
 *  若Lambda中的内容有方法已经实现了 我们可以使用方法引用
 *  （可以理解为方法引入是Lambda表达式的另一种表现形式）
 *
 *  主要有三种语法格式
 *  对象::实例方法名
 *  类::静态方法名
 *  类::实例方法
 *
 *  注意：
 *     ①Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致。
 *     ②若Lambda 参数列表中的第一参数是实例方法的调用者 而第二个参数是实例方法的参数时，可以使用ClassName::Method
 *
 * 构造器引用
 *   ClassName::new
 *    注意：需要调用的构造器参数列表要与函数式接口中抽象方法的参数列表保持一致
 *
 * 数组引用
 *  Type::new
 */
public class TestMethodRef {

    //对象::实例方法名
    @Test
    public void test1(){
        Consumer<String> consumer = (x) -> System.out.println(x);

        PrintStream out = System.out;
        Consumer<String> consumer1 = out::println;

        Consumer<String> consumer2 = System.out::println;
        consumer2.accept("aa");
    }

    //对象::实例方法名
    @Test
    public void test2(){
        Employee employee = new Employee();
        Supplier<String> supplier = () -> employee.getName();
        String s = supplier.get();
        System.out.println(s);

        Supplier<Integer> supplier1 = employee::getAge;
        Integer integer = supplier1.get();
        System.out.println(integer);
    }

    //类::静态方法名
    public void test3(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
        int compare = com.compare(1, 2);
        System.out.println(compare);
        Comparator<Integer> comparator = Integer::compare;
    }

    //类::实例方法
    public void test4(){
        BiPredicate<String,String> biPredicate = (x,y) -> x.equals(y);

        BiPredicate<String,String> biPredicate1 = String::equals;
    }

    //构造器引用
    public void test5(){
        Supplier<Employee> supplier = () -> new Employee();

        Supplier<Employee> supplier1 = Employee::new;
        Employee employee = supplier.get();
    }

    @Test
    public void test6(){
        Function<Integer,Employee> function = (x) -> new Employee();
        Function<Integer,Employee> function1 = Employee::new;
        Employee apply = function1.apply(101);
        System.out.println(apply);

        BiFunction<Integer,Integer,Employee> biFunction = Employee::new;
    }

    //数组引用
    @Test
    public void test7(){
        Function<Integer,String[]> function = (x) -> new String[x];
        String[] strings = function.apply(10);

        Function<Integer ,String[]> function1 = String[]::new;
        String[] strings1 = function1.apply(20);


    }
}
