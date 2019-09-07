package com.qf.ssmexam.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 只是给头顶上为 @Resource给值，@AutoWire不给值
 */
public class SpringResourceTest {
    public static void main(String[] args) throws  Exception{

        Class<MyMethod> clazz = MyMethod.class;

        MyMethod myMethod = clazz.newInstance();  // 通过反射的方式生成一个对象

        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            Annotation[] annotations = field.getAnnotations(); //或去到属性上所有的注解

            for (int j = 0; j < annotations.length; j++) {
                Annotation annotation = annotations[j];
                Class<?> cls = annotation.annotationType();
                if(cls == Resource.class) {
                    field.set(myMethod, "7777777777777777");
                }
            }
        }

        System.out.println(myMethod.name);
        System.out.println(myMethod.phone);

    }
}
