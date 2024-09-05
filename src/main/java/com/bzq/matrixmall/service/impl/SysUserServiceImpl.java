package com.bzq.matrixmall.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.common.constant.SystemConstants;
import com.bzq.matrixmall.converter.UserConverter;
import com.bzq.matrixmall.model.bo.UserBO;
import com.bzq.matrixmall.model.dto.UserAuthInfo;
import com.bzq.matrixmall.model.entity.SysUser;
import com.bzq.matrixmall.mapper.SysUserMapper;
import com.bzq.matrixmall.model.form.UserForm;
import com.bzq.matrixmall.model.query.UserPageQuery;
import com.bzq.matrixmall.model.vo.UserInfoVO;
import com.bzq.matrixmall.model.vo.UserPageVO;
import com.bzq.matrixmall.security.service.PermissionService;
import com.bzq.matrixmall.security.util.SecurityUtils;
import com.bzq.matrixmall.service.SysRoleMenuService;
import com.bzq.matrixmall.service.SysRoleService;
import com.bzq.matrixmall.service.SysUserRoleService;
import com.bzq.matrixmall.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private final SysRoleMenuService roleMenuService;
    private final SysRoleService roleService;
    private final UserConverter userConverter;
    private final PermissionService permissionService;
    private final PasswordEncoder passwordEncoder;
    private final SysUserRoleService userRoleService;

    //新增用户
    @Override
    public boolean saveUser(UserForm userForm) {
        String username = userForm.getUsername();

        long count = this.count(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        Assert.isTrue(count == 0, "用户名已存在");

        // 实体转换 form->entity
        SysUser entity = userConverter.toEntity(userForm);

        // 设置默认加密密码
        String defaultEncryptPwd = passwordEncoder.encode(SystemConstants.DEFAULT_PASSWORD);
        entity.setPassword(defaultEncryptPwd);

        // 新增用户
        boolean result = this.save(entity);

        if (result) {
            // 保存用户角色
            userRoleService.saveUserRoles(entity.getId(), userForm.getRoleIds());
        }

        return result;
    }

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

    //获取用户分页列表
    @Override
    public IPage<UserPageVO> listPagedUsers(UserPageQuery queryParams) {
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<UserBO> page = new Page<>(pageNum, pageSize);

        // 查询数据
        Page<UserBO> userPage = this.baseMapper.listPagedUsers(page, queryParams);

        // 实体转换
        return userConverter.toPageVo(userPage);
    }

    //删除用户
    @Override
    public boolean deleteUsers(String idsStr) {

        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除的用户数据为空");

        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    //重置密码
    @Override
    public boolean resetPassword(Long userId, String password) {
        return this.update(new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getId, userId)
                .set(SysUser::getPassword, passwordEncoder.encode(password))
        );
    }

    //获取用户表单数据
    @Override
    public UserForm getUserFormData(Long userId) {
        return this.baseMapper.getUserFormData(userId);
    }

    //更新用户
    @Override
    @Transactional
    public boolean updateUser(Long userId, UserForm userForm) {
        String username = userForm.getUsername();

        long count = this.count(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username)
                .ne(SysUser::getId, userId)
        );
        Assert.isTrue(count == 0, "用户名已存在");

        // form -> entity
        SysUser entity = userConverter.toEntity(userForm);

        // 修改用户
        boolean result = this.updateById(entity);

        if (result) {
            // 保存用户角色
            userRoleService.saveUserRoles(entity.getId(), userForm.getRoleIds());
        }

        return result;
    }
}
