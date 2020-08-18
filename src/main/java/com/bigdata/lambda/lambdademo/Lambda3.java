package com.bigdata.lambda.lambdademo;

import com.bigdata.lambda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 练习
 */
public class Lambda3 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三",18,999),
            new Employee("李四",18,9990),
            new Employee("王五",18,999),
            new Employee("赵六",18,999),
            new Employee("田七",36,5099)
    );

    @Test
    public void test1(){
        Collections.sort(employees,(e1,e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else {
                return -Integer.compare(e1.getAge(),e2.getAge());
            }
        });

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void test2(){
        String upperCase = toUpperCase("\t\t abcd", (str) -> str.toUpperCase());
        System.out.println(upperCase);
    }

    /**
     * 用于处理字符串
     * @param str
     * @param myFunction1
     * @return
     */
    public String toUpperCase(String str,MyFunction1 myFunction1){
        return myFunction1.getValue(str);
    }

    @Test
    public void test3(){
        String op = op(100L, "hahha", (x, y) -> x + y);
        System.out.println(op);
    }

    //对两个Long行进行运算
    public String op(Long long1,String long2,MyFunction3<Long,String> myFunction3){
       return myFunction3.getValue(long1,long2);
    }

}
