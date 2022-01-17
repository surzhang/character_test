package com.yun.controller;


import cn.hutool.core.codec.Base64;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import com.yun.entity.*;
import com.yun.entity.CharacterA;
import com.yun.service.RoleService;
import com.yun.service.TesterService;
import com.yun.service.UserRoleService;
import com.yun.service.UserService;
import com.yun.shiro.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.*;

/**
 * 用户信息表(User)表控制层
 *
 * @author makejava
 * @since 2021-12-03 15:16:08
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private TesterService testerService;

    /**
     * @ author: zyk
     * @ description:登录方法
     * @ date: 2021/12/3 15:19
     * @ param: [user]
     * @ return: java.lang.String
     */
    @PostMapping("login")
    public ReturnBean login(@RequestBody User user, HttpSession session) {
        //获取shiro的主体，subject
        Subject subject = SecurityUtils.getSubject();
        //构建用户登录的令牌
        UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getPassword());
        //登录操作
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            //用户不存在
            return super.fail(user, "用户名不存在");
        } catch (IncorrectCredentialsException ie) {
            return super.fail(user, "密码不正确");
        }
        Object principal = subject.getPrincipal();
        //转成user对象存储到session中，也可以从subject中找
        User user1 = (User) principal;
        session.setAttribute("user", user1);
        return success(principal);
    }

    /**
     * @ author: zyk
     * @ description:展示用户界面
     * @ date: 2021/12/4 14:54
     * @ param: []
     * @ return: org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/toUser")
    @RequiresPermissions("system:user:view")
    public ModelAndView toUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/showUser");
        return modelAndView;
    }

    /**
     * @ author: zyk
     * @ description:上传图片，修改个人资料
     * @ date: 2021/12/8 21:19
     * @ param: [userId]
     * @ return: org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/toChangeUser")
    public ModelAndView toChangeUser(Integer userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        User user = userService.getOne(queryWrapper);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("user", user);
        modelAndView.setViewName("user/changeUser");
        return modelAndView;
    }

    /**
     * @ author: zyk
     * @ description:分页以及模糊查询方法
     * @ date: 2021/12/4 14:55
     * @ param: [page, limit, user]
     * @ return: com.yun.entity.ReturnBean
     */
    @GetMapping("showUser")
    public ReturnBean selectAll(Long page, Long limit, UserVo user) {
        List<UserVo> userVoList = this.userService.selectAll(page, limit, user);
        return super.success(userVoList, userService.getCount(user));
    }



    /**
     * 新增用户
     * @param user 实体对象
     * @return 新增结果
     */
    @PostMapping("insert")
    public ReturnBean insert(@RequestBody User user, HttpSession session) {
        //添加创建人和时间
        User user1 = (User) session.getAttribute("user");
        user.setCreateBy(user1.getUserName());
        user.setCreateTime(new Date());
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        user.setPassword(ShiroUtil.encryptionBySalt(salt, user.getPassword()));
        //保存到user数据库中
        boolean save = this.userService.save(user);

        if (save) {
            return super.success(user);
        } else {
            return super.fail(user);
        }
    }

    /**
     * @ author: zyk
     * @ description:检验是否重名
     * @ date: 2021/12/5 19:36
     * @ param: [loginName]
     * @ return: com.yun.entity.ReturnBean
     */
    @RequestMapping("checkLoginName")
    public ReturnBean checkLoginName(String loginName) {
        User user = this.userService.checkLoginName(loginName);
        if (user != null) {
            return super.fail(null);
        }
        return super.success(null);
    }

    /**
     * 修改用户
     * @param user 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    public ReturnBean update(@RequestBody User user, HttpSession session) {
        //添加修改人和时间
        User user1 = (User) session.getAttribute("user");
        user.setUpdateBy(user1.getUserName());
        user.setUpdateTime(new Date());
        //返回
        if (user.getStatus() == null) {
            user.setStatus("1");
        }
        //删除中间表数据
        boolean b = this.userService.deleteByUserId(user.getUserId());
        //修改用户
        boolean update = this.userService.updateById(user);
        if (update && b) {
            return super.success(user);
        } else {
            return super.fail(user);
        }
    }

    /**
     * @ author: zyk
     * @ description:用户角色中间表保存数据
     * @ date: 2021/12/8 21:20
     * @ param: [userRoleList]
     * @ return: com.yun.entity.ReturnBean
     */
    @PostMapping("saveBatchUserRole")
    public ReturnBean saveBatchUserRole(@RequestBody List<UserRole> userRoleList) {
        boolean saveBatch = userRoleService.saveOrUpdateBatch(userRoleList);
        if (saveBatch) {
            return success(null);
        } else {
            return fail(null);
        }
    }

    /**
     * 删除用户
     * @param idList 主键结合
     * @return 删除结果
     */
    @RequestMapping("delete")
    public ReturnBean delete(@RequestParam(value = "idList[]", required = false) List<Long> idList) {
        boolean b = userRoleService.deleteBatch(idList);
        if (b) {
            this.userService.removeByIds(idList);
            return super.success(null);
        } else {
            return super.fail(null);
        }
    }

    /**
     * @ author: zyk
     * @ description:通过用户查询选中的角色
     * @ date: 2021/12/8 21:25
     * @ param: [userId]
     * @ return: com.yun.entity.ReturnBean
     */
    @RequestMapping("findSelectRole")
    public ReturnBean findSelectRole(Integer userId) {
        List<UserRole> selectRole = userService.findSelectRole(userId);
        return super.success(selectRole);
    }

    /**
     * @ author: zyk
     * @ description:修改密码并加密
     * @ date: 2021/12/8 21:26
     * @ param: [userId, password]
     * @ return: com.yun.entity.ReturnBean
     */
    @PostMapping("updatePassword")
    public ReturnBean updatePassword(Integer userId, String password) {
        String salt = UUID.randomUUID().toString();
        String password1 = ShiroUtil.encryptionBySalt(salt, password);

        boolean b = userService.updatePassword(userId, password1, salt);
        if (b) {
            return super.success(null);
        }
        return super.fail(null);
    }

//=================================================================================
    /**
     * @description 把前端图片修改为修改图片或上传图片
     * @param: MultipartFile 类型的数据
     * @return: com.yun.entity.UploadEntity
     * @author: mxt
     * @date: 2021/12/6
     */
    @RequestMapping("uploadHead")
    @ResponseBody
    public UploadEntity uploadHead(@RequestParam("file") MultipartFile file) throws IOException {
        //将图片转换成为Base64格式的数据
        String encode = Base64.encode(file.getInputStream());
        //添加"data:image/jpg;base64,"可以直接访问
        String upload = "data:image/jpg;base64," + encode;
        //创建实体类，封装返回到前端的对象
        UploadEntity uploadEntity = new UploadEntity();
        //将状态设为success
        uploadEntity.setResponse("success");
        //将转换后的图片数据返回到前端
        uploadEntity.setName(upload);
        //返回封装后的数据
        return uploadEntity;
    }

    /**
     * @ author: zyk
     * @ description:上传图片页面修改个人资料
     * @ date: 2021/12/8
     * @ param: [user]
     * @ return: com.yun.entity.ReturnBean
     */
    @PutMapping("updateUser")
    public ReturnBean updateUser(User user,HttpSession session) {
        User user1=(User)session.getAttribute("user");
        user1.setAvatar(user.getAvatar());
        session.setAttribute("user",user1);
        //根据userId查询用户信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", user.getUserId());
        User one = userService.getOne(queryWrapper);
        //构建更新语句
        UpdateWrapper wrapper = new UpdateWrapper();
        //修改的内容
        wrapper.set("avatar", user.getAvatar());
        wrapper.set("email", user.getEmail());
        wrapper.set("phonenumber", user.getPhonenumber());
        wrapper.set("update_by", one.getUserName());
        wrapper.set("update_time", new Date());
        //where后面的内容
        wrapper.eq("user_id", user.getUserId());
        //执行更新语句
        boolean update = userService.update(wrapper);
        //判断，trye返回success
        if (update) {
            return success(null);
        } else {
            //失败返回fail
            return fail(null);
        }
    }
//==================================================================================
    /**
     * @ author: zyk
     * @ description:跳转数据展示页面
     * @ date: 2021/12/8 21:30
     * @ param: []
     * @ return: org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("toCount")
    public ModelAndView toCount() {
        ModelAndView modelAndView = new ModelAndView();
        List<TesterVo> allTesterResult = testerService.findAllTesterResult();
        modelAndView.addObject("allTesterResult", allTesterResult);
        Long allCount = testerService.findAllCount();
        modelAndView.addObject("allCount", allCount);
        modelAndView.setViewName("user/showCount");
        Long todayCount = testerService.getTodayCount();
        modelAndView.addObject("todayCount", todayCount);
        return modelAndView;
    }

   /**
    * @ author: zyk
    * @ description:返回每月测试人数
    * @ date: 2021/12/8 21:29
    * @ param: []
    * @ return: com.yun.entity.ReturnBean
    */
    @RequestMapping("getMonthCount")
    public ReturnBean getMonthCount() {
        Month monthCount = testerService.getMonthCount();
        return super.success(monthCount);
    }

    /**
     * @ author: zyk
     * @ description:查询性格
     * @ date: 2021/12/8 21:28
     * @ param: []
     * @ return: com.yun.entity.ReturnBean
     */
    @RequestMapping("findCharacter")
    public ReturnBean findCharacter() {
        CharacterA characterA = testerService.findCharacter();
        return super.success(characterA);
    }


}

