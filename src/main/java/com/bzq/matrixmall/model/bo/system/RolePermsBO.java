package com.bzq.matrixmall.model.bo.system;

import lombok.Data;

import java.util.Set;

//角色权限业务对象
@Data
public class RolePermsBO {
    private String roleCode;//角色编码
    private Set<String> perms;//权限标识集合
}
