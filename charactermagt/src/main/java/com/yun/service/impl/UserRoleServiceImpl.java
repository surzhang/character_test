package com.yun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.dao.UserRoleDao;
import com.yun.entity.UserRole;
import com.yun.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户和角色关联表(UserRole)表服务实现类
 *
 * @author makejava
 * @since 2021-12-05 15:49:02
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {
    @Resource
    private UserRoleDao userRoleDao;
    @Override
    public boolean deleteBatch(List<Long> myList) {
        return this.userRoleDao.deleteBatch(myList);
    }
}

