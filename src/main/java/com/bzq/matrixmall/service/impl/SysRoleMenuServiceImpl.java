package com.bzq.matrixmall.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.mapper.SysRoleMenuMapper;
import com.bzq.matrixmall.model.bo.RolePermsBO;
import com.bzq.matrixmall.model.entity.SysRoleMenu;
import com.bzq.matrixmall.service.SysRoleMenuService;
import com.bzq.matrixmall.common.constant.SecurityConstants;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

//角色菜单业务实现
@Service
@RequiredArgsConstructor
@Slf4j
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    private final RedisTemplate<String, Object> redisTemplate;

    //初始化权限缓存
    @PostConstruct
    public void initRolePermsCache() {
        log.info("初始化权限缓存... ");
        refreshRolePermsCache();
    }

    //获取角色权限集合
    @Override
    public Set<String> getRolePermsByRoleCodes(Set<String> roles) {
        return this.baseMapper.listRolePerms(roles);
    }

    //刷新权限缓存
    @Override
    public void refreshRolePermsCache() {
        // 清理权限缓存
        redisTemplate.opsForHash().delete(SecurityConstants.ROLE_PERMS_PREFIX, "*");

        List<RolePermsBO> list = this.baseMapper.getRolePermsList(null);
        if (CollectionUtil.isNotEmpty(list)) {
            list.forEach(item -> {
                String roleCode = item.getRoleCode();
                Set<String> perms = item.getPerms();
                if (CollectionUtil.isNotEmpty(perms)) {
                    redisTemplate.opsForHash().put(SecurityConstants.ROLE_PERMS_PREFIX, roleCode, perms);
                }
            });
        }
    }

    //刷新权限缓存
    @Override
    public void refreshRolePermsCache(String roleCode) {
        // 清理权限缓存
        redisTemplate.opsForHash().delete(SecurityConstants.ROLE_PERMS_PREFIX, roleCode);

        List<RolePermsBO> list = this.baseMapper.getRolePermsList(roleCode);
        if (CollectionUtil.isNotEmpty(list)) {
            RolePermsBO rolePerms = list.get(0);
            if (rolePerms == null) {
                return;
            }

            Set<String> perms = rolePerms.getPerms();
            if (CollectionUtil.isNotEmpty(perms)) {
                redisTemplate.opsForHash().put(SecurityConstants.ROLE_PERMS_PREFIX, roleCode, perms);
            }
        }
    }

    //刷新权限缓存 (角色编码变更时调用)
    @Override
    public void refreshRolePermsCache(String oldRoleCode, String newRoleCode) {
        // 清理旧角色权限缓存
        redisTemplate.opsForHash().delete(SecurityConstants.ROLE_PERMS_PREFIX, oldRoleCode);

        // 添加新角色权限缓存
        List<RolePermsBO> list = this.baseMapper.getRolePermsList(newRoleCode);
        if (CollectionUtil.isNotEmpty(list)) {
            RolePermsBO rolePerms = list.get(0);
            if (rolePerms == null) {
                return;
            }

            Set<String> perms = rolePerms.getPerms();
            redisTemplate.opsForHash().put(SecurityConstants.ROLE_PERMS_PREFIX, newRoleCode, perms);
        }
    }

}
