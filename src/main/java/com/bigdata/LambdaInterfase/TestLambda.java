package com.bigdata.LambdaInterfase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8 内置四大核心函数式接口
 *  Consumer<T> : 消费型接口
 *   void accept(T t);
 *
 *   Supplier<T> : 供给型接口
 *   T get();
 *
 *   Function<T,R> : 函数型接口
 *    R apply(T);
 *
 *   Predicate<T>: 断言型接口
 *     boolean test(T t);
 *
 */
public class TestLambda {

    /**
     * Consumer<T> : 消费型接口
     */
    @Test
    public void consumerTest(){
        happy(10000,(m) -> System.out.println(m));
    }

    public void happy(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }

    /**
     * Supplier<T> : 供给型接口
     */
    @Test
    public void supplierTest(){
        List<Integer> list = GetNumberList(10, () -> (int) (Math.random() * 1000));
        list.forEach((e) -> {
            System.out.println(e);
        });
    }
    //产生一些整数放入集合中
    public List<Integer> GetNumberList(int number, Supplier<Integer> supplier){
        List list = new ArrayList();
        for(int i = 0; i< number ;i++){
            Integer integer = supplier.get();
            list.add(integer);
        }
        return list;
    }

    //Function<T,R> : 函数型接口
    @Test
    public void FunctionTest(){
        String aaa = strHandler("aaa", (x) -> x.toUpperCase());
        System.out.println(aaa);
    }
    //用于处理字符串
    public String strHandler(String str, Function<String,String> function){
        return function.apply(str);
    }

    //Predicate<T>: 断言型接口
    @Test
    public void PredicateTest(){
        boolean predicate = strPredicate("aaa", (str) -> str.contains("ccc"));
        System.out.println(predicate);
    }
    public boolean strPredicate(String string, Predicate<String> predicate){
       return predicate.test(string);
    }

}
