package com.bigdata.Annotations;

import com.bigdata.StreamAPI.demo.TestTransaction;
import org.junit.Test;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

/**
 * 重复注解与类型注解
 */
public class TestAnnotation {

    @Test
    public void test1() throws NoSuchMethodException {
        Class<TestAnnotation> aClass = TestAnnotation.class;
        Method method = aClass.getMethod("show");
        MyAnnotation[] annotations = method.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation annotation : annotations) {
            System.out.println(annotation.value());
        }
    }

    @MyAnnotation("hello")
    @MyAnnotation("world")
    public void show(){

    }
}
