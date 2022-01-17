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
 * 角色和菜单关联表(RoleMenu)表实体类
 *
 * @author makejava
 * @since 2021-12-05 15:48:36
 */
@SuppressWarnings("serial")
@TableName("tbl_role_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenu extends Model<RoleMenu> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    //角色ID

    private Integer roleId;
    //菜单ID

    private Integer menuId;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }
    }

