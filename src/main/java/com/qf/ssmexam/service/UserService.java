package com.qf.ssmexam.service;

import com.github.pagehelper.PageInfo;
import com.qf.ssmexam.pojo.User;

public interface UserService {

    PageInfo<User> getPageData(int pageNum, int pageSize);
}
