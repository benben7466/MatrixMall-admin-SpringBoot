package com.bzq.matrixmall.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//用户和角色关联表，对应数据库字段

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole {
    private Long userId;//用户ID
    private Long roleId;//角色ID

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
