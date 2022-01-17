package com.yun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yun.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户和角色关联表(UserRole)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-05 15:49:02
 */
public interface UserRoleDao extends BaseMapper<UserRole> {
        //批量删除中间表数据

        boolean deleteBatch (@Param("myList") List<Long> myList);
}

