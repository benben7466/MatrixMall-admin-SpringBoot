package com.bzq.matrixmall.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bzq.matrixmall.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

//用户实体，对应数据库字段
@TableName("sys_user")
@Getter
@Setter
public class SysUser extends BaseEntity {
    private String username;//用户名
    private String nickname;//昵称
    private Integer gender;//性别((1-男 2-女 0-保密)
    private String password;//密码
    private Long deptId;//部门ID
    private String avatar;//用户头像
    private String mobile;//联系方式
    private Integer status;//状态((1-正常 0-禁用)
    private String email;//用户邮箱
    private Long createBy;//创建人 ID
    private Long updateBy;//更新人 ID
}
