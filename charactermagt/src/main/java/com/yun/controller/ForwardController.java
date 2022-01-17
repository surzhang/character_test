package com.yun.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yun.entity.LayUiTree;
import com.yun.entity.Question;
import com.yun.entity.Tester;
import com.yun.entity.User;
import com.yun.service.MenuService;
import com.yun.service.QuestionService;
import com.yun.service.TesterService;
import com.yun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ fileName:ForwardController
 * @ description:
 * @ author:zyk
 * @ createTime:2021/12/2 14:59
 * @ version:1.0.0
 */
@Controller
@RequestMapping("test")
public class ForwardController {
    /**
     * @ author: zyk
     * @ description:服务对象
     * @ date: 2021/12/8 21:49
     * @ param:
     * @ return:
     */
    @Resource
    private QuestionService questionService;

    @Resource
    private TesterService testerService;


    /**
     * @ author: zyk
     * @ description:去登录界面
     * @ date: 2021/12/2 15:44
     * @ param: []
     * @ return: java.lang.String
     */
    @RequestMapping("toTestLogin")
    public String toTestLogin() {
        return "other/testLogin";
    }

    /**
     * @ author: zyk
     * @ description:跳转到测试的页面
     * @ date: 2021/12/2 15:45
     * @ param: [testerId, modelAndView]
     * @ return: org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("toTest")
    public ModelAndView toTest(ModelAndView modelAndView, HttpSession session) {
        //去之前，把所有的有效题目信息带过去
        Tester tester = (Tester) session.getAttribute("tester");
        if (tester == null) {
            modelAndView.setViewName("other/testLogin");
        } else {
            QueryWrapper<Question> queryWrapper = new QueryWrapper();
            //过滤失效的问题
            queryWrapper.eq("status", 0);
            List<Question> questionList = questionService.list(queryWrapper);
            modelAndView.addObject("questionList", questionList);
            modelAndView.addObject("testerId", tester.getId());
            modelAndView.setViewName("other/testQuestion");
        }
        return modelAndView;
    }

    /**
     * @ author: zyk
     * @ description:跳转结果展示页面
     * @ date: 2021/12/8 21:46
     * @ param: [testerId]
     * @ return: org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("toShowResultAnalyse")
    public ModelAndView toShowResultAnalyse(Integer testerId) {
        ModelAndView modelAndView = new ModelAndView();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", testerId);
        Tester tester = testerService.getOne(queryWrapper);
        modelAndView.addObject("tester", tester);
        modelAndView.setViewName("tester/showResultAnalyse");
        return modelAndView;
    }

    /**
     * @ author: zyk
     * @ description:Thanks页面，3秒后跳转注册页面,不再使用
     * @ date: 2021/11/30 20:50
     * @ param: []
     * @ return: java.lang.String
     */
    @RequestMapping("toThanks")
    public String toThanks() {
        return "other/thanks";
    }
}