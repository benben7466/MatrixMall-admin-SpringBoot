package com.bzq.matrixmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.mapper.SysRoleMenuMapper;
import com.bzq.matrixmall.model.entity.SysRoleMenu;
import com.bzq.matrixmall.service.SysRoleMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

//角色菜单业务实现
@Service
@RequiredArgsConstructor
@Slf4j
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    //获取角色权限集合
    @Override
    public Set<String> getRolePermsByRoleCodes(Set<String> roles) {
        return this.baseMapper.listRolePerms(roles);
    }
}
