package com.yun.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yun.entity.EchartsEntity;
import com.yun.entity.ReturnBean;
import com.yun.entity.Tester;
import com.yun.entity.TesterVo;
import com.yun.service.TesterService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 性格测试者(Tester)表控制层
 * @author makejava
 * @since 2021-12-02 14:38:21
 */
@RestController
@RequestMapping("tester")
public class TesterController extends BaseController {
    @Resource
    private TesterService testerService;

    /**
     * @ author: zyk
     * @ description:去测试人员结果页面
     * @ date: 2021/12/4 18:51
     * @ param: []
     * @ return: org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("toTester")
    public ModelAndView toTester() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tester/showTesterResult");
        return modelAndView;
    }

    /**
     * @ author: zyk
     * @ description:带分页的查询
     * @ date: 2021/12/2 15:37
     * @ param: [page, limit, tester]
     * @ return: com.yun.entity.ReturnBean
     */
    @RequestMapping("selectAllPage")
    public ReturnBean selectAllPage(Long page, Long limit, Tester tester) {
        List<TesterVo> testers = testerService.selectAllTester(page, limit, tester);
        return super.success(testers, testerService.getCount(tester));
    }

    /**
     * 新增数据
     *
     * @param tester 实体对象
     * @return 新增结果
     */
    @PostMapping("insert")
    public ReturnBean insert(@RequestBody Tester tester, HttpSession session) {
        session.setAttribute("tester", tester);
        boolean save = this.testerService.saveTester(tester);
        if (save) {
            return super.success(tester);
        } else {
            return super.fail(tester);
        }
    }

    /**
     * @ author: zyk
     * @ description:删除测试人员并删除测试记录
     * @ date: 2021/12/8 21:36
     * @ param: [idList]
     * @ return: com.yun.entity.ReturnBean
     */
    @DeleteMapping("delete")
    public ReturnBean delete(@RequestParam(value = "idList[]", required = false) List<Long> idList) {
        boolean deleteTesterByIds = this.testerService.deleteTesterByIds(idList);
        //删除测试结果
        boolean deleteResult = this.testerService.deleteResult(idList);
        if (deleteTesterByIds && deleteResult) {
            return super.success(null);
        } else {
            return super.fail(null);
        }
    }

    /**
     * @ author: zyk
     * @ description:查询手机号是否被使用
     * @ date: 2021/12/8 21:37
     * @ param: [phone]
     * @ return: com.yun.entity.ReturnBean
     */
    @RequestMapping("findByPhone")
    public ReturnBean findByPhone(String phone) {
        Tester tester = testerService.checkPhone(phone);
        if (tester != null) {
            return success(null, 0L);
        } else {
            return fail(null);
        }
    }

    /**
     * @ author: zyk
     * @ description:去性格分析页面
     * @ date: 2021/12/8 21:38
     * @ param: [testerId]
     * @ return: com.yun.entity.EchartsEntity
     */
    @RequestMapping("selectResultAnalyse")
    @ResponseBody
    public EchartsEntity selectResultAnalyse(Integer testerId) {
        TesterVo testerVo = testerService.selectTesterVoByTesterId(testerId);
        //构建echarts数据
        EchartsEntity entity = new EchartsEntity();
        //设置标题
        EchartsEntity.Title title = new EchartsEntity.Title();
        title.setLeft("center");
        title.setText("性格测试分析");
        title.setSubtext("分析数据");
        entity.setTitle(title);
        EchartsEntity.Tooltip tooltip = new EchartsEntity.Tooltip();
        tooltip.setTrigger("item");
        entity.setTooltip(tooltip);

        List<EchartsEntity.Series> seriesList = new ArrayList<>();
        //扇形图
        EchartsEntity.Series series = new EchartsEntity.Series();
        series.setType("pie");
        series.setName("Character record");
        series.setRadius("50%");

        //设置扇形数据
        List<EchartsEntity.Series.Data> dataList = new ArrayList<>();
        EchartsEntity.Series.Data blueCount = new EchartsEntity.Series.Data();
        blueCount.setName("蓝色性格");
        if (ObjectUtil.isNotEmpty(testerVo.getBlueCount())) {
            blueCount.setValue(testerVo.getBlueCount());
            dataList.add(blueCount);
        }else{
            blueCount.setValue(0);
            dataList.add(blueCount);
        }
        EchartsEntity.Series.Data greenCount = new EchartsEntity.Series.Data();
        greenCount.setName("绿色性格");
        if (ObjectUtil.isNotEmpty(testerVo.getGreenCount())) {
            greenCount.setValue(testerVo.getGreenCount());
            dataList.add(greenCount);
        }else{
            greenCount.setValue(0);
            dataList.add(greenCount);
        }
        EchartsEntity.Series.Data yellowCount = new EchartsEntity.Series.Data();
        yellowCount.setName("黄色性格");
        if (ObjectUtil.isNotEmpty(testerVo.getYellowCount())) {
            yellowCount.setValue(testerVo.getYellowCount());
            dataList.add(yellowCount);
        }else{
            yellowCount.setValue(0);
            dataList.add(yellowCount);
        }
        EchartsEntity.Series.Data redCount = new EchartsEntity.Series.Data();
        redCount.setName("红色性格");
        if (ObjectUtil.isNotEmpty(testerVo.getRedCount())) {
            redCount.setValue(testerVo.getRedCount());
            dataList.add(redCount);
        }else{
            redCount.setValue(0);
            dataList.add(redCount);
        }
        series.setData(dataList);
        //样式
        EchartsEntity.Series.Emphasis emphasis = new EchartsEntity.Series.Emphasis();
        EchartsEntity.Series.Emphasis.ItemStyle itemStyle = new EchartsEntity.Series.Emphasis.ItemStyle();
        itemStyle.setShadowBlur(10);
        itemStyle.setShadowOffsetX(0);
        itemStyle.setShadowColor("rgba(0, 0 , 0 , 0.5)");
        emphasis.setItemStyle(itemStyle);
        series.setEmphasis(emphasis);
        seriesList.add(series);
        entity.setSeries(seriesList);
        EchartsEntity.Legend legend = new EchartsEntity.Legend();
        legend.setLeft("left");
        legend.setOrient("vertical");
        entity.setLegend(legend);
        return entity;
    }
}
