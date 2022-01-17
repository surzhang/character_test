package com.yun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yun.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户和角色关联表(UserRole)表服务接口
 *
 * @author makejava
 * @since 2021-12-05 15:49:02
 */
public interface UserRoleService extends IService<UserRole> {
    boolean deleteBatch (List<Long> myList);
}

