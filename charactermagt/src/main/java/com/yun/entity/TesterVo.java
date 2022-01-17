package com.yun.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ fileName:TesterVo
 * @ description:性格测试者(Tester)在页面上展示的实体类
 * @ author:zyk
 * @ createTime:2021/12/2 14:48
 * @ version:1.0.0
 */
@Data
public class TesterVo implements Serializable {

    private static final long serialVersionUID = -51157744451271229L;
    /**
     * 主键
     */
    private Integer testerId;
    /**
     * 测试者姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 测试时间
     */
    private Date createTime;
    private Integer redCount;
    private Integer blueCount;
    private Integer yellowCount;
    private Integer greenCount;

}
