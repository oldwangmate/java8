package com.bigdata.interfaceDemo;

public class SubClass /*extends MyClass*/ implements MyFunction,MyInterface {

    @Override
    public String getName() {
        return MyInterface.super.getName();
    }
}
