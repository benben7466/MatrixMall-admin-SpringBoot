package com.bzq.matrixmall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.model.entity.SysRole;
import com.bzq.matrixmall.model.form.RoleForm;
import com.bzq.matrixmall.model.query.RolePageQuery;
import com.bzq.matrixmall.model.vo.RolePageVO;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Set;

public interface SysRoleService  extends IService<SysRole> {
    //获取最大范围的数据权限
    Integer getMaximumDataScope(Set<String> roles);

    //角色下拉列表
    List<Option<Long>> listRoleOptions();

    //角色分页列表
    Page<RolePageVO> getRolePage(RolePageQuery queryParams);

    //保存角色
    boolean saveRole(RoleForm roleForm);

    //批量删除角色
    boolean deleteRoles(String ids);

    //获取角色表单数据
    RoleForm getRoleForm(Long roleId);

    //获取角色的菜单ID集合
    List<Long> getRoleMenuIds(Long roleId);

    //修改角色的资源权限
    boolean assignMenusToRole(Long roleId, List<Long> menuIds);
}
