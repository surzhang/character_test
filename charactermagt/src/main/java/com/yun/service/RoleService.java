package com.yun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yun.entity.Menu;
import com.yun.entity.Role;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2021-12-03 09:01:33
 */
public interface RoleService extends IService<Role> {
    List<Menu> findSelectMenu(Integer roleId);
   boolean deleteByRoleId(Integer roleId);

    /**
     * @author: 李心雨
     * @description: 检验角色名
     * @date: 2021/12/9 15:53
     * @param roleName
     * @return Role
     */
    Role checkRoleName(String roleName);
    /**
     * @author: 李心雨
     * @description: 校验关键字
     * @date: 2021/12/9 16:47
     * @param roleKey
     * @return
     */
    Role checkRoleKey(String  roleKey);
}

