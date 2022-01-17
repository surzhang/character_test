package com.yun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yun.entity.LayUiTree;
import com.yun.entity.Menu;

import java.util.List;
import java.util.Set;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2021-12-03 10:26:20
 */
public interface MenuService extends IService<Menu> {

    List<LayUiTree> selectAllMenu();

    //根据用户登录名查询对应的所有菜单

    List<LayUiTree> selectAllMenuByName(String loginName);

    //根据登陆用户名查询所有的权限

    Set<String> selectAllPermsByName(String loginName);


}

