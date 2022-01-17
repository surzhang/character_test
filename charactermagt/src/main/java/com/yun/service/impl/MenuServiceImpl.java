package com.yun.service.impl;

import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.dao.MenuDao;
import com.yun.dao.TesterDao;
import com.yun.entity.LayUiTree;
import com.yun.entity.Menu;
import com.yun.service.MenuService;
import com.yun.utils.TreeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2021-12-03 10:26:20
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {
    @Resource
    private MenuDao menuDao;

    /**
     * 查询所有的菜单，并组装成tree格式的
     *
     * @return
     */
    @Override
    public List<LayUiTree> selectAllMenu() {
        //查询所有的菜单
        List<Menu> menus = menuDao.selectAllMenu();
        //并组装成tree格式的
        return TreeUtils.getChildPerms(menus, 0);
    }
    /**
     * 根据登陆用户名查询所有菜单
     * @param loginName
     * @return
     */
    @Override
    public List<LayUiTree> selectAllMenuByName(String loginName) {
        //查询所有的菜单
        List<Menu> menus = menuDao.selectAllMenuByName(loginName);
        //并组装成tree格式的
        return TreeUtils.getChildPerms(menus, 0);
    }

    /**
     * 根据登陆用户名查询所有的权限
     * @param loginName
     * @return
     */
    @Override
    public Set<String> selectAllPermsByName(String loginName) {
        //查询所有的菜单
        List<Menu> menus = menuDao.selectAllMenuByName(loginName);
        Set<String> perms = new HashSet<String>();
        for (Menu menu : menus) {
            String perm = menu.getPerms();
            if (null != perm && perm.length() > 0) {
                perms.add(perm);
            }
        }
        return perms;
    }
}

