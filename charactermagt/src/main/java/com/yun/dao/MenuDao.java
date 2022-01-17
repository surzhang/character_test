package com.yun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yun.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-03 10:26:20
 */
public interface MenuDao extends BaseMapper<Menu> {

    List<Menu> selectAllMenu();

    List<Menu> selectAllMenuByName(String loginName);


}

