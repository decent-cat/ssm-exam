package com.qf.ssmexam.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Class.forName("")
 *
 * 1.Java中任何一个对象，任何一个类、接口、枚举等都有一个Class类型的对象。
 * 2.由一个类生成无论多少个对象，都对应同一个Class类型的对象。
 * 3.类或者对象锁对应的Class类型对象，中间包含整个类中所有的属性以及方法。
 *
 * 4.要想使用Java的反射，首先必须要获取的Class类型的对象。Class<T> class
 * 5. 获取Class类型的对象总共三种方式：
 *     A. 类名.class
 *     B. 对象.getClass();
 *     C. Class.forName("全类名");
 * 6. 反射是运行时的状态。
 */
public class ReflectTest {
    public static void main(String[] args) throws  Exception{
        /**
        Date date1 = new Date();
        Date date2 = new Date();
        Date date3 = new Date();

        Class<?> clazz = Date.class;
        Class<?> clazz1 = date1.getClass();
        Class<?> clazz2 = date2.getClass();
        Class<?> clazz3 = date3.getClass();

        System.out.println(clazz == clazz1);
        System.out.println(clazz3 == clazz2);
        System.out.println(clazz1 == clazz2);
         */

        Class<MyMethod> clazz = MyMethod.class;

        Field[] fields = clazz.getFields();

        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i]);
        }

        System.out.println("===========================================");

        Field[] fields1 = clazz.getDeclaredFields();
        for (int i = 0; i < fields1.length; i++) {
            System.out.println(fields1[i]);
        }

        System.out.println("======================================");

//        Method[] methods = clazz.getMethods();
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            System.out.println(method.getName());
            System.out.println(method.getModifiers());
            System.out.println("----------------------------");
        }

        System.out.println("================================================");

        //获取一个方法，方法名为add, 方法的参数有四个，依次为int int int int
        Method method = clazz.getDeclaredMethod("add", int.class, int.class);

        MyMethod myMethod = new MyMethod();

        // java反射的反射，
        System.out.println(method.invoke(myMethod, 4, 4));

        // 反射将复杂的简单化，将简单的问题复杂化
        System.out.println("=====================================");

        Method method1 = clazz.getDeclaredMethod("substract", int.class, int.class);
        System.out.println(method1.invoke(null, 78, 23));  //静态方法是不依赖与对象存在的

        System.out.println("=======================================");

        Method method2 = clazz.getDeclaredMethod("input");

        MyTestAnnotation mta = method2.getAnnotation(com.qf.ssmexam.test.MyTestAnnotation.class);

        System.out.println(mta.value());
        System.out.println(mta.seasons().length);
    }
}
