package com.bzq.matrixmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.common.constant.SystemConstants;
import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.converter.RoleConverter;
import com.bzq.matrixmall.mapper.SysRoleMapper;
import com.bzq.matrixmall.model.entity.SysRole;
import com.bzq.matrixmall.security.util.SecurityUtils;
import com.bzq.matrixmall.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

//角色业务实现类

@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final RoleConverter roleConverter;

    //获取最大范围的数据权限
    @Override
    public Integer getMaximumDataScope(Set<String> roles) {
        Integer dataScope = this.baseMapper.getMaximumDataScope(roles);
        return dataScope;
    }

    //角色下拉列表
    @Override
    public List<Option<Long>> listRoleOptions() {
        // 查询数据
        List<SysRole> roleList = this.list(new LambdaQueryWrapper<SysRole>()
                .ne(!SecurityUtils.isRoot(), SysRole::getCode, SystemConstants.ROOT_ROLE_CODE)
                .select(SysRole::getId, SysRole::getName)
                .orderByAsc(SysRole::getSort)
        );

        // 实体转换
        return roleConverter.entities2Options(roleList);
    }
}
