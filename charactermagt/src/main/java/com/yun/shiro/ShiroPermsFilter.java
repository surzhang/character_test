package com.yun.shiro;

import cn.hutool.core.util.StrUtil;
import com.yun.controller.BaseController;
import com.yun.entity.ReturnBean;
import com.yun.utils.MyConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ author: zyk
 * @ description:扩展shiro授权拦截器
 * @ date: 2021/12/7 15:40
 * @ param: 
 * @ return: 
 */
public class ShiroPermsFilter extends PermissionsAuthorizationFilter {
   /**
    * @ author: zyk
    * @ description:授权失败的时候执行的内容
    * @ date: 2021/12/7 15:40
    * @ param: [request, response, mappedValue]
    * @ return: boolean
    */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        HttpServletResponse httpServletResponse= (HttpServletResponse) response;
        if(StrUtil.isNotEmpty(httpServletRequest.getHeader(MyConstants.HEADER_TYPE)) && httpServletRequest.getHeader(MyConstants.HEADER_TYPE).equalsIgnoreCase(MyConstants.HEADER_CONTENT)){
            //如果是ajax请求
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setCharacterEncoding("utf-8");
            BaseController baseController= new BaseController();
            ReturnBean returnBean = baseController.fail(null, "没有操作权限");
            ObjectMapper objectMapper= new ObjectMapper();
            //将对象转换成json字符串
            String asString = objectMapper.writeValueAsString(returnBean);
            httpServletResponse.getWriter().write(asString);
        }else {
            //如果不是ajax请求
            httpServletResponse.sendRedirect("/unau.html");
        }
        return false;
    }
}
