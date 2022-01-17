package com.yun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户和角色关联表(UserRole)表实体类
 *
 * @author makejava
 * @since 2021-12-05 15:49:02
 */
@SuppressWarnings("serial")
@TableName("tbl_user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole extends Model<UserRole> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer Id;
    //用户ID
    private Integer userId;
    //角色ID
    private Integer roleId;


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

