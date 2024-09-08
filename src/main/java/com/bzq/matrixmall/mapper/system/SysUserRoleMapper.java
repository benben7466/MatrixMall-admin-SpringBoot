package com.bzq.matrixmall.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bzq.matrixmall.model.entity.system.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

//用户角色访问层
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    //获取角色绑定的用户数
    int countUsersForRole(Long roleId);
}
