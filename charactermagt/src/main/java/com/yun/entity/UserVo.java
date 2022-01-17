package com.yun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ fileName:UserVo
 * @ description:
 * @ author:李心雨
 * @ createTime:2021/12/3 11:05
 * @ version:1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {
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
    //删除标志（0代表存在 1代表删除）
    @TableLogic
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
    //部门名称
    private String deptName;
    //角色ID
    private Integer roleId;

}
