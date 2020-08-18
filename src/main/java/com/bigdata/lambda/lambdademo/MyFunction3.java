package com.bigdata.lambda.lambdademo;

@FunctionalInterface
public interface MyFunction3<T,R> {
     R getValue(T t1,R t2);
}
