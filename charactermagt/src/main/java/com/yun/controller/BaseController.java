package com.yun.controller;

import com.yun.entity.ReturnBean;

/**
 * @ fileName:BaseController
 * @ description:所有controller的父类
 * @ author:zyk
 * @ createTime:2021/12/2 14:41
 * @ version:1.0.0
 */
public class BaseController {
    /**
     * @ author: zyk
     * @ description:请求成功返回方法
     * @ date: 2021/12/2 14:44
     * @ param: [object, count]
     * @ return: com.yun.entity.ReturnBean
     */
    public ReturnBean success(Object object, Long... count) {
        ReturnBean returnBean = new ReturnBean();
        returnBean.setCode(0);
        returnBean.setMsg("操作成功");
        returnBean.setData(object);
        if (count != null && count.length > 0) {
            returnBean.setCount(count[0]);
        }
        return returnBean;
    }

    /**
     * @ author: zyk
     * @ description:请求失败返回的方法
     * @ date: 2021/12/2 14:44
     * @ param: [object]
     * @ return: com.yun.entity.ReturnBean
     */
    public ReturnBean fail(Object object) {
        ReturnBean returnBean = new ReturnBean();
        returnBean.setCode(1);
        returnBean.setMsg("操作失败");
        returnBean.setData(object);
        return returnBean;
    }

    public ReturnBean fail(Object object,String message) {
        ReturnBean returnBean = new ReturnBean();
        returnBean.setCode(1);
        returnBean.setMsg(message);
        returnBean.setData(object);
        return returnBean;
    }
}
