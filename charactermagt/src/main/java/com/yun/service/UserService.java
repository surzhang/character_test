package com.yun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yun.entity.Role;
import com.yun.entity.User;
import com.yun.entity.UserRole;
import com.yun.entity.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息表(User)表服务接口
 *
 * @author makejava
 * @since 2021-12-03 15:16:08
 */
public interface UserService extends IService<User> {
    boolean deleteByUserId(Integer roleId);
    User checkLoginName(String loginName);
    List<UserVo> selectAll(@Param("page") Long page  , @Param("limit") Long limit, @Param("userVo") UserVo userVo);
    Long getCount(@Param("userVo")UserVo userVo);
    List<UserRole> findSelectRole(Integer userId);
    boolean updatePassword(@Param("userId") Integer userId,@Param("password") String password,String salt);
}

