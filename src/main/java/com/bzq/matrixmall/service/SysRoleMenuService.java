package com.bzq.matrixmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.model.entity.SysRoleMenu;

import java.util.Set;

//角色菜单业务接口
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    //获取角色权限集合
    Set<String> getRolePermsByRoleCodes(Set<String> roles);
}
