package com.yun.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * 问题表(Question)表实体类
 *
 * @author makejava
 * @since 2021-12-02 14:37:47
 */
@SuppressWarnings("serial")
@Data
@TableName("tbl_question")
public class Question extends Model<Question> {
    //问题编号
    @TableId(value = "id" ,type = IdType.AUTO)
    private Integer id;
    
    private String question;
    
    private String optionA;
    
    private String optionB;
    
    private String optionC;
    
    private String optionD;
    //预留字段
    private Integer type;
    //状态
    private Integer status;
    //创建时间
    private Date createTime;
    //创建人
    private String creator;
    //修改时间
    private Date updateTime;
    //修改人
    private String updater;
    
    private Integer flag;



    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    }

