package com.qf.ssmexam.dao;


import com.qf.ssmexam.base.BaseDao;
import com.qf.ssmexam.pojo.Knowledge;
import com.qf.ssmexam.pojo.Knowledge;
import java.util.List;

public interface KnowledgeDao extends BaseDao<Knowledge> {

    List<Knowledge> getAll();
}
