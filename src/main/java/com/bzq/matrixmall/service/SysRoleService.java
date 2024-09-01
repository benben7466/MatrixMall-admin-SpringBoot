package com.bzq.matrixmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.model.entity.SysRole;

import java.util.Set;

public interface SysRoleService  extends IService<SysRole> {
    //获取最大范围的数据权限
    Integer getMaximumDataScope(Set<String> roles);
}
