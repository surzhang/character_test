package com.yun.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yun.entity.Dept;
import com.yun.entity.ReturnBean;
import com.yun.service.DeptService;
import com.yun.utils.MyConstants;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 部门表(Dept)表控制层
 *
 * @author makejava
 * @since 2021-12-03 10:13:58
 */
@RestController
@RequestMapping("dept")
public class DeptController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private DeptService deptService;

    /**
     * @ author: zyk
     * @ description:跳转部门管理页面
     * @ date: 2021/12/4 18:39
     * @ param: []
     * @ return: org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/toDept")
    public ModelAndView toDept() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dept/showDept");
        return modelAndView;
    }

    /**
     * @ author: zyk
     * @ description:分页以及模糊查询
     * @ date: 2021/12/4 18:38
     * @ param: [page, limit, dept]
     * @ return: com.yun.entity.ReturnBean
     */
    @RequestMapping("selectAll")
    public ReturnBean selectAll(Long page, Long limit, Dept dept) {
        //重新构建分页对象
        if (page == null) {
            page = MyConstants.page;
            limit = MyConstants.limit;
        }
        Page<Dept> pageDept = new Page<>(page, limit);
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotEmpty(dept.getDeptName())) {
            queryWrapper.like("dept_name", dept.getDeptName());
        }
        if (ObjectUtil.isNotEmpty(dept.getCreateBy())) {
            queryWrapper.like("create_by", dept.getCreateBy());
        }
        if (ObjectUtil.isNotEmpty(dept.getUpdateBy())) {
            queryWrapper.like("update_by", dept.getUpdateBy());
        }
        Page<Dept> deptPage = deptService.page(pageDept, queryWrapper);
        return super.success(deptPage.getRecords(), deptPage.getTotal());
    }

    /**
     * 新增数据
     * @param dept 实体对象
     * @return 新增结果
     */
    @PostMapping("insertDept")
    public ReturnBean insert(@RequestBody Dept dept, HttpSession session) {
        Object userName = session.getAttribute("userName");
        dept.setCreateBy(userName.toString());
        dept.setCreateTime(new Date());
        boolean save = this.deptService.save(dept);
        if (save) {
            return super.success(dept);
        } else {
            return super.fail(dept);
        }
    }

    /**
     * 修改数据
     * @param dept 实体对象
     * @return 修改结果
     */
    @PutMapping("updateDept")
    public ReturnBean update(@RequestBody Dept dept, HttpSession session) {
        Object userName = session.getAttribute("userName");
        dept.setUpdateBy(userName.toString());
        dept.setUpdateTime(new Date());
        boolean update = this.deptService.updateById(dept);
        if (update) {
            return super.success(dept);
        } else {
            return super.fail(dept);
        }
    }

    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @RequestMapping("deleteDept")
    public ReturnBean delete(@RequestParam(value = "idList[]", required = false) List<Long> idList) {
        boolean removeByIds = this.deptService.removeByIds(idList);
        if (removeByIds) {
            return super.success(null);
        } else {
            return super.fail(null);
        }
    }

    @RequestMapping("findOne")
    public ReturnBean findOne(String deptName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("dept_name", deptName);
        Dept one = deptService.getOne(queryWrapper);
        if (one == null) {
            //不存在
            return success(one, 1L);
        } else {
            return fail(null);
        }

    }
}

