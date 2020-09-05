package com.bigdata.optional;

import com.bigdata.lambda.Employee;
import com.bigdata.optional.demo.Godness;
import com.bigdata.optional.demo.Man;
import com.bigdata.optional.demo.NewMan;
import org.junit.Test;

import java.util.Optional;

public class TestOptional {
    /*
      Optional 容器类的常用方法
        Optional.of(T t) : 创建一个 Optional 实例
        Optional.empty() : 创建一个空的 Optional 实例
        Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
        isPresent() : 判断是否包含值
        orElse(T t) : 如果调用对象包含值，返回该值，否则返回t
        orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
        map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
        flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
    */

    @Test
    public void test4(){
        Optional<Employee> optional = Optional.ofNullable(new Employee("这是", 18, 888, Employee.Status.BUSY));
        Optional<String> str = optional.map((e) -> e.getName());
        System.out.println(str.get());

        Optional<String> s = optional.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(s.get());
    }


    @Test
    public void test3(){
        Optional<Employee> employee = Optional.ofNullable(null);
      /*  if(employee.isPresent()){
            System.out.println(employee.get());
        }*/

        /*Employee emp = employee.orElse(new Employee("这是",18,888,Employee.Status.BUSY));
        System.out.println(emp);*/

        Employee employee1 = employee.orElseGet(() -> {
            return new Employee();
        });
        System.out.println(employee1);
    }
    @Test
    public void test2(){
        Optional<Object> empty = Optional.empty();
        System.out.println(empty.get());
    }

    @Test
    public void test1(){
        Optional<Employee> op = Optional.of(null);
        Employee employee = op.get();
        System.out.println(employee);
    }


    /**
     * 练习
     *
     */
    @Test
    public void test5(){
        Man man = new Man();
        String name = getGodnessName(man);
        System.out.println(name);

        Optional<Godness> godness = Optional.ofNullable(new Godness("高挑女神"));
        Optional<NewMan> optional = Optional.ofNullable(new NewMan(godness));
        String name2 = getGodnessName2(optional);
        System.out.println(name2);
    }

    private String getGodnessName2(Optional<NewMan> newMan){
       return newMan.orElse(new NewMan())
               .getGodness()
               .orElse(new Godness("苍老师"))
               .getName();
    }

    //获取一个男人心中的女神名字
    private String getGodnessName(Man man){
        if(man != null){
            Godness godness = man.getGodness();
            if(godness != null){
                return godness.getName();
            }
        }
        return "苍老师";
    }
}
