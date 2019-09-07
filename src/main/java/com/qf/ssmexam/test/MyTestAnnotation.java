package com.qf.ssmexam.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTestAnnotation {

    // 注解是很很特殊的类，value本质是属性，但是必须要求写成方法的形式
    String value(); // value是默认值

    Season[] seasons();
}
