package com.qf.ssmexam.controller;

import com.qf.ssmexam.commons.Constants;
import com.qf.ssmexam.pojo.SysUser;
import com.qf.ssmexam.service.SysUserService;
import com.qf.ssmexam.utils.PasswdCodec;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 开发中密码是不允许明文存储。
 * md5 -> 不可逆
 * 12356 -> 1;;2::3##4%%5$$6 -> 可以实现可逆
 *
 * md5不安全，彩虹表。
 *
 * 原密码   加密后的密码
 *   1         abc
 *  234       xyz
 */
@RestController
public class LoginController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/login")
    public Object login(String username, String password, HttpSession session) {
        SysUser sysUser = sysUserService.selectOneByName(username);

        Map<String, Object> map = new HashMap<>();

        if(sysUser == null) {
            map.put("code", -1);
            map.put("msg", "用户名或密码错误");
            return map;
        }

        String dbPassword = sysUser.getPassword(); //数据中存储的处理后的密码
        String salt = sysUser.getSalt(); //盐值

        String cryptPwd = PasswdCodec.encryPwd(salt, password);

        if(dbPassword.equals(cryptPwd)) {  //密码相同
            session.setAttribute(Constants.USER_SESSION_KEY, sysUser);
            map.put("code", 1);
            map.put("msg", "success");
        }else{ //密码不同
            map.put("code", -1);
            map.put("msg", "用户名或密码错误");
        }
        return map;
    }
}
