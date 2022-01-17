package com.yun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * (TestResult)表实体类
 *
 * @author makejava
 * @since 2021-12-02 14:37:07
 */
@SuppressWarnings("serial")
@TableName("tbl_test_result")
@Data
public class TestResult extends Model<TestResult> {
    /**
     * @ author: zyk
     * @ description:主键自增
     * @ date: 2021/12/2 14:46
     * @ param:
     * @ return:
     */
    @TableId(value = "id" ,type = IdType.AUTO)
    private Integer id;
    //测试人员id
    private Integer testerId;
    //测试题id
    private Integer questionId;
    //题目答案
    private String answer;



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

