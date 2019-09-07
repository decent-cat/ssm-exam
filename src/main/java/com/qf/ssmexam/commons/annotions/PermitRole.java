package com.qf.ssmexam.commons.annotions;


import com.qf.ssmexam.commons.enums.RoleType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target表示的是作用的地点，METHOD只能加在方法上
 *                          TYPE可以加在到类上
 *
 * RetentionPolicy.RUNTIME: 在运行阶段也可以获取到
 * RetentionPolicy.CLASS  : 该注解会编译到字节码文件中，但是程序运行的过程中无法获取。
 * RetentionPolicy.SOURCE : 表示只是在编码阶段有效， 不会编译到字节码文件中
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermitRole {

    /**
     * 定义访问类型
     * @return
     */
    RoleType[] value();
}
