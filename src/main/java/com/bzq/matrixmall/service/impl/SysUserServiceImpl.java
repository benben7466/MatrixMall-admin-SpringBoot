package com.bzq.matrixmall.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.model.dto.UserAuthInfo;
import com.bzq.matrixmall.model.entity.SysUser;
import com.bzq.matrixmall.mapper.SysUserMapper;
import com.bzq.matrixmall.service.SysRoleMenuService;
import com.bzq.matrixmall.service.SysRoleService;
import com.bzq.matrixmall.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private final SysRoleMenuService roleMenuService;
    private final SysRoleService roleService;

    @Override
    public UserAuthInfo getUserAuthInfo(String username) {
        UserAuthInfo userAuthInfo = this.baseMapper.getUserAuthInfo(username);
        if (userAuthInfo != null) {
            Set<String> roles = userAuthInfo.getRoles();
            if (CollectionUtil.isNotEmpty(roles)) {
                Set<String> perms = roleMenuService.getRolePermsByRoleCodes(roles);
                userAuthInfo.setPerms(perms);
            }

            // 获取最大范围的数据权限
            Integer dataScope = roleService.getMaximumDataScope(roles);
            userAuthInfo.setDataScope(dataScope);
        }
        return userAuthInfo;
    }
}
