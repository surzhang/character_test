package com.yun.entity;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.Data;

/**
 * @ author: zyk
 * @ description:layui数据表格专用实体类
 * @ date: 2021/12/2 14:43
 * @ param:
 * @ return:
 */
@Data
public class ReturnBean<T>{
    private int code;
    private String msg;
    private Long count;
    private T data;
}
