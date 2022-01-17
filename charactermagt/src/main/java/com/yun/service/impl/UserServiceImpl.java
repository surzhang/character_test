package com.yun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.dao.UserDao;
import com.yun.entity.Role;
import com.yun.entity.User;
import com.yun.entity.UserRole;
import com.yun.entity.UserVo;
import com.yun.service.UserService;
import com.yun.utils.MyConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表(User)表服务实现类
 *
 * @author makejava
 * @since 2021-12-03 15:16:08
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public List<UserVo> selectAll(Long page, Long limit, UserVo userVo) {
        //优化代码，不分页的时候，默认第一页，一页显示10条
        if (page == null) {
            page = MyConstants.page;
            limit = MyConstants.limit;
        }
        List<UserVo> userVoList = userDao.selectAll(page, limit, userVo);
        return userVoList;
    }

    @Override
    public Long getCount(UserVo userVo) {
        Long count = userDao.getCount(userVo);
        return count;
    }

    @Override
    public List<UserRole> findSelectRole(Integer userId) {
        return this.userDao.findSelectRole(userId);
    }

    @Override
    public boolean updatePassword(Integer userId, String password,String salt) {
        int i = userDao.updatePassword(userId, password,salt);
        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public User checkLoginName(String loginName) {
        User user = userDao.checkLoginName(loginName);
       return user;
    }

    @Override
    public boolean deleteByUserId(Integer roleId) {
        int i = this.userDao.deleteByUserId(roleId);
        if (i>0){
            return true;
        }
        return false;
    }
}

