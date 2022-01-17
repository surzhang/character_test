package com.yun.shiro;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yun.entity.User;
import com.yun.service.MenuService;
import com.yun.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @ author: zyk
 * @ description:权限管理
 * @ date: 2021/12/7 9:23
 * @ param:
 * @ return:
 */
public class MyRealm  extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /**
     * @ author: zyk
     * @ description:授权功能,授权代码开始执行
     * @ date: 2021/12/3 16:56
     * @ param: [principals]
     * @ return: org.apache.shiro.authz.AuthorizationInfo
     */
    @Autowired
    private MenuService menuService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //此处连接数据库获取perms字段
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        //根据登录名查询权限
        Set<String> perms = menuService.selectAllPermsByName(user.getLoginName());
        simpleAuthorizationInfo.addStringPermissions(perms);
        return simpleAuthorizationInfo;
    }
    /**
     * @ author: zyk
     * @ description:认证功能,认证代码开始执行
     * @ date: 2021/12/3 16:56
     * @ param: [token]
     * @ return: org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取令牌对象
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) token;
        //第一步校验用户是否存在
        String loginName = usernamePasswordToken.getUsername();
        QueryWrapper<User> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("login_name",loginName);
        User user = userService.getOne(queryWrapper);
        if(null==user){
            return null;
        }
        //第二步校验密码是否正确
        //获取密码的密文
        String password = user.getPassword();
        //获取密码的盐值
        String salt = user.getSalt();
        /**
         *      * @param principal         the 'primary' principal associated with the specified realm.
         *      * @param hashedCredentials the hashed credentials that verify the given principal.
         *      * @param credentialsSalt   the salt used when hashing the given hashedCredentials
         *      * @param realmName         the realm from where the principal and credentials were acquired.
         */
        ByteSource byteSource = ByteSource.Util.bytes(salt);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password, byteSource, getName());
        return simpleAuthenticationInfo;
    }
}
