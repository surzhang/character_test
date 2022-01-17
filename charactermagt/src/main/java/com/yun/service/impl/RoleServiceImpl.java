package com.yun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.dao.RoleDao;
import com.yun.entity.Menu;
import com.yun.entity.Role;
import com.yun.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2021-12-03 09:01:33
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {
    @Resource
    private RoleDao roleDao;

    @Override
    public List<Menu> findSelectMenu(Integer roleId){
        return roleDao.findSelectMenu(roleId);
    }

    @Override
    public boolean deleteByRoleId(Integer roleId) {
        int i= this.roleDao.deleteByRoleId(roleId);
        if (i>0){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public Role checkRoleName(String roleName) {
        Role role = roleDao.checkRoleName(roleName);
        return role;
    }

    @Override
    public Role checkRoleKey(String roleKey) {
        Role role = roleDao.checkRoleKey(roleKey);
        return role;
    }


}

