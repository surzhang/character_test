package com.yun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yun.entity.Menu;
import com.yun.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-03 09:01:33
 */
public interface RoleDao extends BaseMapper<Role> {
   List<Menu> findSelectMenu(Integer roleId);
   int deleteByRoleId(Integer roleId);
   Role checkRoleName(String roleName);

   Role checkRoleKey(@Param("roleKey") String roleKey);
}

