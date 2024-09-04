package com.bzq.matrixmall.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//用户和角色关联表，对应数据库字段

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_role")
public class SysUserRole implements Serializable {
    private Long userId;//用户ID
    private Long roleId;//角色ID

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
