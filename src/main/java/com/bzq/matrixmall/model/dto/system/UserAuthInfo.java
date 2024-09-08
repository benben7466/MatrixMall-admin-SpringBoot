package com.bzq.matrixmall.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

//用户认证信息
@Schema(description = "用户认证信息响应对象")
@Data
public class UserAuthInfo {
    @Schema(description = "用户ID")
    private Integer userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户密码")
    private String password;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "部门ID")
    private Long deptId;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "角色")
    private Set<String> roles;

    @Schema(description = "权限")
    private Set<String> perms;

    @Schema(description = "数据范围")
    private Integer dataScope;
}
