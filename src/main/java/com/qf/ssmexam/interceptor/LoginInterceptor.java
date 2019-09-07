package com.qf.ssmexam.interceptor;

import com.qf.ssmexam.commons.Constants;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器目的处理用户登录，如果没有登录让用户到deng登录页面
 */
public class LoginInterceptor implements HandlerInterceptor {

//    private static final String loginPath = "login.html"; //登录页面
    private static final String loginAction = "login.do";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        String uri = request.getRequestURI();

        // 如果用户是提交登录信息，让用户直接过, login.html不会走拦截器，因为在web.xml中配置 *.do
        if(uri.endsWith(loginAction)) {

            // 用户直接在浏览器地址栏输入，是一个GET请求，让用户再回去，必须按照正常的方式登录
            if("GET".equals(request.getMethod())) {
                response.sendRedirect("login.html");
                return false;
            }
            return true;
        }

        Object sessionObj = session.getAttribute(Constants.USER_SESSION_KEY);

        // 如果sessionObj为空，表示用户没有登录成功过，
        // 如果用户登录成功过， session.setAttribute(Constants.USER_SESSION_KEY, sysUser); 该代码肯定执行过
        if(null == sessionObj) {
            response.sendRedirect("login.html");
            return false;
        }

        return true;
    }
}
