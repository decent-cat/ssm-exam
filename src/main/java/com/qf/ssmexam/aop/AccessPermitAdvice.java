package com.qf.ssmexam.aop;

import com.qf.ssmexam.commons.Constants;
import com.qf.ssmexam.commons.annotions.PermitRole;
import com.qf.ssmexam.commons.enums.RoleType;
import com.qf.ssmexam.pojo.SysUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ServletContextPropertyUtils;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

@Component
@Aspect
public class AccessPermitAdvice {

    /**
     * ProceedingJoinPoint pjp是参数类型，这是Aspect所要求的，
     * 通过该方法可以拿到方法的所有的信息：访问修饰符、方法名、参数、注解
     *
     * 当方法头顶加了@PermitRole的注解， 在调用该方法的时候，会触发以下的通知执行。
     */
    @Around("@annotation(com.qf.ssmexam.commons.annotions.PermitRole)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//        pjp.getArgs(); //获取方法所有的参数
//        pjp.getSignature()  // 获取到方法签名信息，返回值为Signature， 然后在获取方法的本省

        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();  // Method
//        methodSignature.getModifiers()  //获取到方法的访问修饰符，1 2 4
//        methodSignature.getName();  //获取到方法的名字

        // 获取java反射中的Method类型的对象
        Method method = methodSignature.getMethod();

        // 获取到方法头顶上的注解
        PermitRole pr= method.getAnnotation(PermitRole.class);

        /**
         *  @PermitRole({RoleType.teacher, RoleType.student})
         *  pr.value可以获取到 RoleType.teacher, RolType.student.
         */
        RoleType[] rts = pr.value();  //获取到RoleType

        ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        // 要获取到当前用户的角色
        HttpServletRequest request = servletRequestAttribute.getRequest();

        HttpServletResponse response = servletRequestAttribute.getResponse();

        HttpSession session = request.getSession(); //获取Session

        SysUser sysUser = (SysUser)session.getAttribute(Constants.USER_SESSION_KEY);

        String role = sysUser.getRole(); //获取当前用户的角色

        RoleType roleType = RoleType.valueOf(role); //将对应的字符串转换为对应的枚举类型

        boolean flag = false;  // 假设数组没有

        // 当有一个满足情况，将flag改为true, 循环退出
        for (int i = 0; i < rts.length; i++) {
            if(roleType == rts[i]) {
                flag = true;
                break;
            }
        }

        if(flag){  // 表示有权限
            Object obj = pjp.proceed();  // proceed()方法类似于 Filter,表示接着往下执行
            return obj;
        }else { //没有权限
            response.sendRedirect("noPermit.html");
            return null;
        }
    }
}
