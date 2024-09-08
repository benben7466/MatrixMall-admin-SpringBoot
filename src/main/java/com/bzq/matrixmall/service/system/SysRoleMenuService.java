package com.bzq.matrixmall.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.model.entity.system.SysRoleMenu;

import java.util.List;
import java.util.Set;

//角色菜单业务接口
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    //获取角色权限集合
    Set<String> getRolePermsByRoleCodes(Set<String> roles);

    //刷新权限缓存(所有角色)
    void refreshRolePermsCache();

    //刷新权限缓存(指定角色)
    void refreshRolePermsCache(String roleCode);

    //刷新权限缓存(修改角色编码时调用)
    void refreshRolePermsCache(String oldRoleCode, String newRoleCode);

    //获取角色拥有的菜单ID集合
    List<Long> listMenuIdsByRoleId(Long roleId);
}
