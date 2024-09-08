package com.bzq.matrixmall.model.bo.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

//用户持久化对象
@Data
public class UserBO {
    private Long id;//用户ID
    private String username;//账户名
    private String nickname;//昵称
    private String mobile;//手机号
    private Integer gender;//性别(1->男；2->女)
    private String avatar;//头像URL
    private String email;//邮箱
    private Integer status;//状态: 1->启用;0->禁用
    private String deptName;//部门名称
    private String roleNames;//角色名称，多个使用英文逗号(,)分割

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;//创建时间
}
