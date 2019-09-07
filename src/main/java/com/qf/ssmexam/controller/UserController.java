package com.qf.ssmexam.controller;

import com.qf.ssmexam.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping
    public Object getPageData() {
        return userService.getPageData(1, 5);
    }
}
