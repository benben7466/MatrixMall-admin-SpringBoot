package com.bzq.matrixmall.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bzq.matrixmall.model.bo.system.RolePermsBO;
import com.bzq.matrixmall.model.entity.system.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

//角色菜单访问层
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    //获取角色权限集合
    Set<String> listRolePerms(Set<String> roles);

    //获取权限和拥有权限的角色列表
    List<RolePermsBO> getRolePermsList(String roleCode);

    //获取角色拥有的菜单ID集合
    List<Long> listMenuIdsByRoleId(Long roleId);
}
