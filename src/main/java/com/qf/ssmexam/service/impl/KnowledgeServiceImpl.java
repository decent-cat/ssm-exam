package com.qf.ssmexam.service.impl;

import com.qf.ssmexam.dao.KnowledgeDao;
import com.qf.ssmexam.pojo.Knowledge;
import com.qf.ssmexam.service.KnowledgeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class KnowledgeServiceImpl implements KnowledgeService {

    @Resource
    private KnowledgeDao knowledgeDao;

    @Override
    public List<Knowledge> getAll() {
        return knowledgeDao.getAll();
    }
}
