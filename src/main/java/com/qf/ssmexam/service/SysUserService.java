package com.qf.ssmexam.service;

import com.qf.ssmexam.pojo.SysUser;

public interface SysUserService {

    SysUser selectOneByName(String nikename);
}
