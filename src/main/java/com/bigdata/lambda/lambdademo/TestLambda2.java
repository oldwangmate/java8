package com.bigdata.lambda.lambdademo;

import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Lambda 表达式中的基础语法 java8中引入了新的操作符 —> 该操作符称作箭头操作符 或者lambda表达式操作符
 *  左侧：lambda 表达式的参数列表
 *  右侧 lambda 表达式中所需要执行的功能 即lambda体
 *
 *  语法格式一： 无参 无返回值
 *            （） -> System.out.println("hello lambda")
 *  语法格式二： 有一个参数 并且无返回值
 *            (str) -> System.out.println(str);
 *   语法格式三： 如果lambda中只有一个参数 小括号可以不写
 *            str -> System.out.println(str)
 *   语法格式四： 两个以上的参数 并且lambda体中有多条语句 有返回值
 *            Comparator<Integer> comparator  = (x,y) -> {
 *             System.out.println("函数式接口");
 *             return  Integer.compare(x,y);
 *         };
 *   语法格式五： 如果有两个参数 lambda体中只有一个条语句 return和大阔可以省略不写
 *         Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
 *
 *   语法格式六： lambda的参数列表的数据类型可以省略不写 因为JVM的编译器可以通过上下文推断出数据类型 这个过程我们叫做 “类型推断”
 *              (Integer x,Integer y) -> Integer.compare(x,y)
 *
 * 左右遇一括号省
 * 左侧推断类型省
 *
 * Lambda 需要函数式接口的支持
 * 什么是函数式接口： 接口中只有一个抽象方法的接口 称之为函数式接口  可以使用注解@FunctionalInterface
 *                    可以检查是否是函数式接口
 */
public class TestLambda2 {


    @Test
    public void test1(){
        int num = 0;  //JDK1.7以前必须是final的
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world" + num);
            }
        };
        runnable.run();
        System.out.println("----------------------------------");
        Runnable runnable1 = () -> {
            System.out.println("hello lambda");
        };
        runnable1.run();
    }

    @Test
    public void test2(){
        Consumer<String>  consumer = str -> System.out.println(str);
        consumer.accept("lambda");
    }


    @Test
    public void test3(){
        Comparator<Integer> comparator  = (x,y) -> {
            System.out.println("函数式接口");
            return  Integer.compare(x,y);
        };
        System.out.println(comparator.compare(1,2));
    }

    @Test
    public void test4() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
    }

    @Test
    public void test5() {
//       String[] strings = {"aaa","bbb","ccc"};
//        List<String>
        show(new HashMap<>());
    }

    public void show(Map<String,Integer> map){

    }

    /**
     * 需求：对一个数进行运算
     */
    @Test
    public void test6(){
        Integer operation = operation(100, (x) -> x * x);
        System.out.println(operation);

        System.out.println(operation(200 , (y) -> y + 200));
    }
    public Integer operation(Integer num,MyFunction<Integer> myFunction){
        return myFunction.getValue(num);
    }
}
