package com.bigdata.interfaceDemo;

public interface MyFunction {

    default String getName(){
        return "哈哈";
    }

}
