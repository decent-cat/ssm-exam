package com.qf.ssmexam.controller;

import com.qf.ssmexam.commons.Constants;
import com.qf.ssmexam.commons.enums.RoleType;
import com.qf.ssmexam.pojo.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class IndexController {

    @RequestMapping("/index")
    public String toIndex(HttpSession session) {
        SysUser sysUser = (SysUser)session.getAttribute(Constants.USER_SESSION_KEY);

        String role = sysUser.getRole(); //获取当前登录的用户的角色

        if(RoleType.student.toString().equals(role)) {
            return "student/index";
        }else if(RoleType.teacher.toString().equals(role)) {
            return "teacher/index";
        }else {
            return "redirect:/error.html";
        }
    }
}
