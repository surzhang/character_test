package com.yun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yun.entity.Question;


import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 问题表(Question)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-02 14:37:47
 */

public interface QuestionDao extends BaseMapper<Question> {

}

