package com.qf.ssmexam.test;

import org.springframework.beans.factory.annotation.Autowired;

public class MyMethod {

    public MyMethod() {
        System.out.println("no arg constructor invoked...");
    }

    @AutoWire
    public String name;

    @Resource
    public String phone;

    private String age;

    private void output(String str) {
        System.out.println(str);
    }

    public int add(int a, int c , int b){
        return a + b + c;
    }

    public int add(int a, int c){
        return a + c;
    }

    public static int substract(int a, int c) {
        return a - c;
    }

    /**
     * 1.注解的值默认是赋值给value, 类似于 @MyTestAnnotation("helloworld")这种形式，
     * 表示的意思赋值给value; 如果是其他值，那么必须要指定参数名。
     * 2. 如果是给注解中的多个值赋值的时候，value也必须要显式的指定
     */
    @MyTestAnnotation(value="helloworld", seasons={Season.fall, Season.summer})
    public void input() {
        System.out.println("input mehtod invoked...");
    }
}
