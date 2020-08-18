package com.bigdata.lambda.lambdademo;

@FunctionalInterface
public interface MyFunction<T> {

    public Integer getValue(Integer integer);
}
