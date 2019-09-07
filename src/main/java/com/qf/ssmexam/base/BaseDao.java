package com.qf.ssmexam.base;

import com.qf.ssmexam.pojo.User;
import tk.mybatis.mapper.common.*;

public interface BaseDao<T> extends MySqlMapper<T>,
        ExampleMapper<T>, BaseMapper<T>, IdsMapper<T>, ConditionMapper<T> {
}
