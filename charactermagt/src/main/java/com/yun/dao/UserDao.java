package com.yun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yun.entity.Role;
import com.yun.entity.User;
import com.yun.entity.UserRole;
import com.yun.entity.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息表(User)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-03 15:16:08
 */
public interface UserDao extends BaseMapper<User> {
    //根据id删除用户
    int deleteByUserId(Integer roleId);

    //校验登录名
    User checkLoginName(String loginName);

    //数据表格中查询所有数据
    List<UserVo> selectAll(@Param("page") Long page, @Param("limit") Long limit, @Param("userVo") UserVo userVo);

    //查询总人数

    Long getCount(@Param("userVo") UserVo userVo);

    //保存用户

    boolean saveUser(@Param("user") User user);

    //查询选中的角色

    List<UserRole> findSelectRole(Integer userId);

    //修改密码

    int updatePassword(@Param("userId") Integer userId, @Param("password") String password, @Param("salt") String salt);
}

