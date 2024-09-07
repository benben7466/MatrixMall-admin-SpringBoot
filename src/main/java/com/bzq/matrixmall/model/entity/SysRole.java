package com.bzq.matrixmall.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bzq.matrixmall.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

//角色实体，对应数据库字段
@TableName("sys_role")
@Getter
@Setter
public class SysRole extends BaseEntity {
    private String name;//角色名称
    private String code;//角色编码
    private Integer sort;//显示顺序
    private Integer status;//角色状态(1-正常 0-停用)
    private Integer dataScope;//数据权限
    private Long createBy;//创建人 ID
    private Long updateBy;//更新人 ID
}
