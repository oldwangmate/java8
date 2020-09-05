package com.bigdata.interfaceDemo;

/**
 * 类优先原则
 *  若一个接口中定义了一个默认方法，而另外一个父类中或接口中又定义了一个同名的方法时
 *  选择父类中的方法，如果一个父类提供了具体的实现，那么接口中具有同名称和参数默认方法就会被忽略
 *  接口冲突，如果一个父接口提供一个默认方法，而另外一个接口也提供了一个具有相同名称和参数列表的方法
 *  不管是否是默认方法，那么必须覆盖该方法来解决冲突
 */
public class TestDefaultInterface {
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        System.out.println(subClass.getName());
        MyInterface.show();
    }
}
