package com.yun.ann;

import com.yun.entity.Log;
import com.yun.entity.User;
import com.yun.service.LogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ fileName:LogFilter
 * @ description:
 * @ author:zyk
 * @ createTime:2021/12/5 12:56
 * @ version:1.0.0
 */
@Component
@Aspect
public class LogFilter {

    @Resource
    private LogService logService;

    //切入点描述 这个是controller包的切入点
    @Pointcut("execution(public * com.yun.controller..*.*(..))")
    public void controllerLog() {
    }//签名，可以理解成这个切入点的一个名称

    @Around("controllerLog()")
    public Object logBeforeController(ProceedingJoinPoint joinPoint) {
        //获取方法的参数
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        //保存日志信息到数据库中
        Log log = new Log();
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        User user = new User();
        if (principal != null) {
            user = (User) principal;
        }
        log.setLoginName(user.getLoginName());
        log.setUserName(user.getUserName());
        //获取请求的ip地址
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String remoteAddr = request.getRemoteAddr();
        log.setIp(remoteAddr);
        //获取类名
        String className = joinPoint.getTarget().getClass().getName();
        log.setClassName(className);
        //获取方法名
        String[] split = signature.toString().split("\\.");
        String method = split[split.length - 1];
        log.setMethodName(method);
        //获取参数
        String paramString = Arrays.toString(args);
        log.setParams(paramString);
        //设置时间
        log.setCreateTime(new Date());
        logService.save(log);
        try {
            //业务方法执行
            Object proceed = joinPoint.proceed(args);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
