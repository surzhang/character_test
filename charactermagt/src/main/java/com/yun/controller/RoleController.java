package com.yun.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yun.entity.*;
import com.yun.service.MenuService;
import com.yun.service.RoleMenuService;
import com.yun.service.RoleService;
import com.yun.utils.MyConstants;
import com.yun.utils.TreeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色信息表(Role)表控制层
 *
 * @author makejava
 * @since 2021-12-03 09:01:33
 */
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;
    @Resource
    private RoleMenuService roleMenuService;

    /**
     * @ author: zyk
     * @ description:去角色管理页面
     * @ date: 2021/12/4 18:49
     * @ param: []
     * @ return: org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/toRole")
    public ModelAndView toShowRole() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("role/showRole");
        return modelAndView;
    }

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param role 查询实体
     * @return 所有数据
     */
    @RequestMapping("selectAll")
    public ReturnBean selectAll(Long page, Long limit, Role role) {
        //重新构建分页对象
        if (page == null) {
            page = MyConstants.page;
            limit = MyConstants.limit;
        }
        Page<Role> rolePage = new Page<>(page, limit);
        Page<Role> page1 = roleService.page(rolePage, new QueryWrapper<>(role));
        return super.success(page1.getRecords(), page1.getTotal());
    }

    /**
     * 新增角色
     *
     * @param role 实体对象
     * @return 新增结果
     */
    @PostMapping("insertRole")
    public ReturnBean insert(@RequestBody Role role, HttpSession session) {
        User user = (User) session.getAttribute("user");
        role.setCreateBy(user.getUserName());
        role.setCreateTime(new Date());
        boolean save = this.roleService.save(role);
        if (save) {
            return super.success(role);
        } else {
            return super.fail(role);
        }
    }

    @PostMapping("saveBatchRoleMenu")
    public ReturnBean saveBatchTestResult(@RequestBody List<RoleMenu> roleMenuList, Integer roleId) {
        boolean saveBatch = roleMenuService.saveOrUpdateBatch(roleMenuList);
        if (saveBatch) {
            return success(null);
        } else {
            return fail(null);
        }
    }

    /**
     * 修改角色
     *
     * @param role 实体对象
     * @return 修改结果
     */
    @PutMapping("updateRole")
    public ReturnBean update(@RequestBody Role role, HttpSession session) {
        User user = (User) session.getAttribute("user");
        role.setUpdateBy(user.getLoginName());
        role.setUpdateTime(new Date());
        boolean b = this.roleService.deleteByRoleId(role.getRoleId());
        boolean update = this.roleService.updateById(role);
        if (update && b) {
            return super.success(role);
        } else {
            return super.fail(role);
        }
    }

    /**
     * 删除角色
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("deleteRole")
    public ReturnBean delete(@RequestParam(value = "idList[]", required = false) List<Long> idList) {
        boolean removeByIds = this.roleService.removeByIds(idList);
        if (removeByIds) {
            return super.success(null);
        } else {
            return super.fail(null);
        }
    }

    @GetMapping("findSelectMenu")
    public ReturnBean findSelectMenu(Integer roleId) {
        List<Menu> selectMenu = roleService.findSelectMenu(roleId);
        List<LayUiTree> childPerms = TreeUtils.getChildPerms(selectMenu, 0);
        if (childPerms != null && childPerms.size() > 0) {
            //查询成功，传回前端
            return success(childPerms);
        }
        return fail(null);
    }

    /**
     * @ author: zyk
     * @ description:校验角色名
     * @ date: 2021/12/9 17:40
     * @ param: [roleName]
     * @ return: com.yun.entity.ReturnBean
     */
    @RequestMapping("checkRoleName")
    public ReturnBean checkRoleName(String roleName) {
        Role role = roleService.checkRoleName(roleName);
        if (role != null) {
            return super.fail(null);
        } else {
            return super.success(null);
        }
    }

    /**
     * @param roleKey
     * @return
     * @author: 李心雨
     * @description: 校验关键字
     * @date: 2021/12/9 16:48
     */
    @RequestMapping("checkRoleKey")
    public ReturnBean checkRoleKey(String roleKey) {
        Role role = roleService.checkRoleKey(roleKey);
        if (role != null) {
            return super.fail(null);
        } else {
            return super.success(null);
        }
    }
}

