package com.yun.utils;

/**
 * @ author: zyk
 * @ description:项目使用的常量类
 * @ date: 2021/12/2 15:02
 * @ param:
 * @ return:
 */
public class MyConstants {
    /**
     * 默认分页的起始页
     */
    public static  final Long page=1L;
    /**
     * 默认的分页的pagesize，一页显示多少行
     */
    public static  final Long limit=10L;

    //加密算法
    public static final String algorithmName="MD5";
    //加密次数
    public static final   int hashIterations=1000;
    /**
     * 默认ajxa请求头类型
     */
    public  static  final String HEADER_TYPE="X-Requested-With";
    /**
     * 默认ajxa请求头内容
     */
    public  static  final String HEADER_CONTENT="XMLHttpRequest";
}
