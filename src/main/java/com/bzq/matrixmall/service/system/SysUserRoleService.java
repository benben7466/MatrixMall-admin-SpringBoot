package com.bzq.matrixmall.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.model.entity.system.SysUserRole;

import java.util.List;

public interface SysUserRoleService extends IService<SysUserRole> {
    //保存用户角色
    void saveUserRoles(Long userId, List<Long> roleIds);

    //判断角色是否存在绑定的用户
    boolean hasAssignedUsers(Long roleId);
}
