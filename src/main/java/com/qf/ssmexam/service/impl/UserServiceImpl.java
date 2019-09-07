package com.qf.ssmexam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.ssmexam.dao.UserDao;
import com.qf.ssmexam.pojo.User;
import com.qf.ssmexam.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public PageInfo<User> getPageData(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<User> list = userDao.selectAll();

        return PageInfo.of(list);
    }
}
