package com.yun.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yun.entity.*;
import com.yun.service.LogService;
import com.yun.utils.MyConstants;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * (Log)表控制层
 *
 * @author makejava
 * @since 2021-12-05 12:34:56
 */
@RestController
@RequestMapping("log")
public class LogController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private LogService logService;

    /**
     * @ author: zyk
     * @ description:去测试人员结果页面
     * @ date: 2021/12/4 18:51
     * @ param: []
     * @ return: org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("toLog")
    public ModelAndView toLog() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("log/showLog");
        return modelAndView;
    }

    /**
     * @ author: zyk
     * @ description:分页以及模糊查询
     * @ date: 2021/12/9 15:40
     * @ param: [page, limit, log]
     * @ return: com.yun.entity.ReturnBean
     */
    @RequestMapping("showLog")
    public ReturnBean showLog(Long page, Long limit, Log log) {
        //重新构建分页对象
        if (page == null) {
            page = MyConstants.page;
            limit = MyConstants.limit;
        }
        Page<Log> pageObj = new Page<>(page, limit);
        //问题条件查询对象
        QueryWrapper<Log> logQueryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotEmpty(log.getLoginName())) {
            logQueryWrapper.like("login_name", log.getLoginName());
        }
        if (ObjectUtil.isNotEmpty(log.getMethodName())) {
            logQueryWrapper.like("method_name", log.getMethodName());
        }
        if (ObjectUtil.isNotEmpty(log.getCreateTime())) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date today = log.getCreateTime();
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            // 今天+1天
            c.add(Calendar.DAY_OF_MONTH, 1);
            Date tomorrow = c.getTime();
            logQueryWrapper.between("create_time", today, tomorrow);
        }
        Page<Log> logPage = this.logService.page(pageObj, logQueryWrapper);
        return super.success(logPage.getRecords(), logPage.getTotal());
    }

    /**
     * 新增数据
     *
     * @param log 实体对象
     * @return 新增结果
     */
    @PostMapping("insertLog")
    public ReturnBean insert(@RequestBody Log log) {
        boolean save = this.logService.save(log);
        if (save) {
            return super.success(log);
        } else {
            return super.fail(log);
        }
    }

    /**
     * 修改数据
     *
     * @param log 实体对象
     * @return 修改结果
     */
    @PutMapping("updateLog")
    public ReturnBean update(@RequestBody Log log) {
        boolean update = this.logService.updateById(log);
        if (update) {
            return super.success(log);
        } else {
            return super.fail(log);
        }
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @RequestMapping("deleteLog")
    public ReturnBean delete(@RequestParam(value = "idList[]", required = false) List<Long> idList) {
        boolean removeByIds = this.logService.removeByIds(idList);
        if (removeByIds) {
            return super.success(null);
        } else {
            return super.fail(null);
        }
    }
}

