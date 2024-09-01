package com.bzq.matrixmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.mapper.SysRoleMapper;
import com.bzq.matrixmall.model.entity.SysRole;
import com.bzq.matrixmall.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

//角色业务实现类

@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    //获取最大范围的数据权限
    @Override
    public Integer getMaximumDataScope(Set<String> roles) {
        Integer dataScope = this.baseMapper.getMaximumDataScope(roles);
        return dataScope;
    }
}
