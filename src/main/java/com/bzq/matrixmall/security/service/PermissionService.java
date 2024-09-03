package com.bzq.matrixmall.security.service;

import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.bzq.matrixmall.common.constant.SecurityConstants;

import java.util.*;

//SpringSecurity 权限校验
@Component("ss")
@RequiredArgsConstructor
@Slf4j
public class PermissionService {
    private final RedisTemplate<String, Object> redisTemplate;

    //从缓存中获取角色权限列表
    public Set<String> getRolePermsFormCache(Set<String> roleCodes) {
        // 检查输入是否为空
        if (CollectionUtil.isEmpty(roleCodes)) {
            return Collections.emptySet();
        }

        Set<String> perms = new HashSet<>();
        // 从缓存中一次性获取所有角色的权限
        Collection<Object> roleCodesAsObjects = new ArrayList<>(roleCodes);
        List<Object> rolePermsList = redisTemplate.opsForHash().multiGet(SecurityConstants.ROLE_PERMS_PREFIX, roleCodesAsObjects);

        for (Object rolePermsObj : rolePermsList) {
            if (rolePermsObj instanceof Set) {
                @SuppressWarnings("unchecked")
                Set<String> rolePerms = (Set<String>) rolePermsObj;
                perms.addAll(rolePerms);
            }
        }

        return perms;
    }
}
