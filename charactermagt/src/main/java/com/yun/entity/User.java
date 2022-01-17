package com.yun.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 用户信息表(User)表实体类
 *
 * @author makejava
 * @since 2021-12-03 15:16:08
 */
@SuppressWarnings("serial")
@Data
@TableName("tbl_user")
public class User extends Model<User> implements Serializable {
    //用户ID
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    //部门ID
    private Integer deptId;
    //登录账号
    private String loginName;
    //用户昵称
    private String userName;
    //用户邮箱
    private String email;
    //手机号码
    private String phonenumber;
    //用户性别（0男 1女 2未知）
    private String sex;
    
    private String avatar;
    //密码
    private String password;
    //盐加密
    private String salt;
    //帐号状态（0正常 1停用）
    private String status;
    //删除标志（0代表存在 2代表删除）
    private String flag;
    //创建者
    private String createBy;
    //创建时间
    private Date createTime;
    //更新者
    private String updateBy;
    //更新时间
    private Date updateTime;
    //备注
    private String remark;



    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.userId;
    }
    }

