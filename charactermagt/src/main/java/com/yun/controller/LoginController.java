package com.yun.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yun.entity.LayUiTree;
import com.yun.entity.User;
import com.yun.service.MenuService;
import com.yun.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ fileName:LoginController
 * @ description:
 * @ author:zyk
 * @ createTime:2021/12/3 23:24
 * @ version:1.0.0
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    /**
     * @ author: zyk
     * @ description:去登录，后台系统登录界面
     * @ date: 2021/12/8 21:51
     * @ param: []
     * @ return: java.lang.String
     */
    @RequestMapping("toLogin")
    public String toLogin() {
        return "login";
    }
    /**
     * @create by: Teacher陈（86521760@qq.com）
     * @description: 登陆成功之后去主页
     * @create time: 2021/12/3 16:00
     * @param
     * @return ModelAndView
     */
    @RequestMapping("toHome")
    public ModelAndView toHome(ModelAndView modelAndView,HttpSession session){
        //根据用户id查询用户信息
        User user=(User)session.getAttribute("user");
        System.out.println("session中"+user.toString());
        modelAndView.addObject("user",user);
        //将要去index页面之前，保存部分数据到model
        //session存储数据
        session.setAttribute("userName",user.getLoginName());
        modelAndView.addObject("loginName",user.getLoginName());
        List<LayUiTree> menus = menuService.selectAllMenuByName(user.getLoginName());
        modelAndView.addObject("menus",menus);
        //根据用户id查询所有的菜单信息
        modelAndView.setViewName("index");
        return modelAndView;
    }


    /**
     * 注销
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
    /**
     * 注销
     * @return
     */
    @RequestMapping("/toUnau")
    public String toUnau(){
        System.out.println("没有权限！！！！");
        return "unau";
    }
}
