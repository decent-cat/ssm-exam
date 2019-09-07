package com.qf.ssmexam.service.impl;

import com.qf.ssmexam.dao.SysUserDao;
import com.qf.ssmexam.pojo.SysUser;
import com.qf.ssmexam.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    @Override
    public SysUser selectOneByName(String nickname) {
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("nickname", nickname);
        return sysUserDao.selectOneByExample(example);
    }
}
