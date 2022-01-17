package com.yun.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yun.entity.LayUiTree;
import com.yun.entity.Menu;
import com.yun.entity.ReturnBean;
import com.yun.entity.User;
import com.yun.service.MenuService;
import com.yun.utils.TreeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 菜单权限表(Menu)表控制层
 *
 * @author makejava
 * @since 2021-12-03 10:26:20
 */
@RestController
@RequestMapping("menu")
public class MenuController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private MenuService menuService;


    /**
     * @ author: zyk
     * @ description:跳转菜单显示页面
     * @ date: 2021/12/4 18:43
     * @ param: []
     * @ return: org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("toMenu")
    public ModelAndView toMenu(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User user =(User) session.getAttribute("user");
        modelAndView.addObject("user",user);
        modelAndView.setViewName("menu/showMenu");
        return modelAndView;
    }


    /**
     * @ author: zyk
     * @ description:查询所有菜单
     * @ date: 2021/12/4 18:44
     * @ param: []
     * @ return: com.yun.entity.ReturnBean
     */
    @RequestMapping("selectAllMenu")
    @ResponseBody
    public ReturnBean selectAllMenu() {
        //查询全部菜单
        List<Menu> list = menuService.list();
        if (list != null && list.size() > 0) {
            //查询成功，传回前端
            return success(list, (long) list.size());
        }
        return fail(null);
    }

    /**
     * @ author: zyk
     * @ description:查询所有菜单，树形数据是data.data
     * @ date: 2021/12/4 18:44
     * @ param: []
     * @ return: com.yun.entity.ReturnBean
     */
    @RequestMapping("selectAllMenus")
    @ResponseBody
    public ReturnBean selectAllMenus() {
        //查询全部菜单
        List<Menu> list = menuService.list();
        List<LayUiTree> childPerms = TreeUtils.getChildPerms(list, 0);
        if (childPerms != null && childPerms.size() > 0) {
            //查询成功，传回前端
            return success(childPerms);
        }
        return fail(null);
    }

    /**
     * @ author: zyk
     * @ description:增加菜单
     * @ date: 2021/12/4 18:45
     * @ param: [menu]
     * @ return: com.yun.entity.ReturnBean
     */
    @RequestMapping("insertMenu")
    @ResponseBody
    public ReturnBean insertMenu(Menu menu) {
        menu.setCreateTime(new Date());
        boolean save = menuService.save(menu);
        if (save) {
            return success(null);
        }
        return fail(null);
    }

    /**
     * @ author: zyk
     * @ description:删除菜单
     * @ date: 2021/12/4 18:45
     * @ param: [menuId]
     * @ return: com.yun.entity.ReturnBean
     */
    @PutMapping("deleteMenuById")
    @ResponseBody
    public ReturnBean deleteMenuById(Integer menuId) {
        boolean b = menuService.removeById(menuId);
        if (b) {
            return success(null);
        }
        return fail(null);
    }

    /**
     * @ author: zyk
     * @ description:查询父id
     * @ date: 2021/12/4 18:46
     * @ param: [menuId]
     * @ return: com.yun.entity.ReturnBean
     */
    @GetMapping("findParentNameId")
    @ResponseBody
    public ReturnBean findParentNameId(Integer menuId) {
        Menu byId = menuService.getById(menuId);
        Menu byId1 = null;
        if (byId.getParentId() == 0) {
            byId1 = menuService.getById(byId.getParentId());
            if (byId1 != null) {
                return success(byId1);
            }
        }
        if (byId != null) {
            return success(byId);
        }

        return fail(null);
    }

    /**
     * @ author: zyk
     * @ description:更新菜单
     * @ date: 2021/12/4 18:46
     * @ param: [menu]
     * @ return: com.yun.entity.ReturnBean
     */
    @PutMapping("updateMenu")
    @ResponseBody
    public ReturnBean updateMenu(Menu menu) {
        menu.setUpdateTime(new Date());
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("menu_id", menu.getMenuId());
        boolean update = menuService.update(menu, updateWrapper);
        if (update) {
            return success(null, null);
        }
        return fail(null);
    }

    @RequestMapping("findOne")
    @ResponseBody
    public ReturnBean findOne(String menuName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("menu_name", menuName);
        Menu one = menuService.getOne(queryWrapper);
        if (one == null) {
            //不存在
            return success(one, 1L);
        } else {
            return fail(null);
        }
    }

}

