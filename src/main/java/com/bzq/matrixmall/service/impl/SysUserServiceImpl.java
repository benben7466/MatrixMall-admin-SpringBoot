package com.bzq.matrixmall.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.converter.UserConverter;
import com.bzq.matrixmall.model.dto.UserAuthInfo;
import com.bzq.matrixmall.model.entity.SysUser;
import com.bzq.matrixmall.mapper.SysUserMapper;
import com.bzq.matrixmall.model.vo.UserInfoVO;
import com.bzq.matrixmall.security.service.PermissionService;
import com.bzq.matrixmall.security.util.SecurityUtils;
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
    private final UserConverter userConverter;
    private final PermissionService permissionService;

    //根据用户名获取认证信息
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

    //获取登录用户信息
    @Override
    public UserInfoVO getCurrentUserInfo() {
        String username = SecurityUtils.getUsername();

        // 获取登录用户基础信息
        SysUser user = this.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username)
                .select(
                        SysUser::getId,
                        SysUser::getUsername,
                        SysUser::getNickname,
                        SysUser::getAvatar
                )
        );

        // entity->VO
        UserInfoVO userInfoVO = userConverter.toUserInfoVo(user);

        // 用户角色集合
        Set<String> roles = SecurityUtils.getRoles();
        userInfoVO.setRoles(roles);

        // 用户权限集合
        if (CollectionUtil.isNotEmpty(roles)) {
            Set<String> perms = permissionService.getRolePermsFormCache(roles);
            userInfoVO.setPerms(perms);
        }

        return userInfoVO;
    }
}
