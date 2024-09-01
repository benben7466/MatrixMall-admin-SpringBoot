package com.bzq.matrixmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bzq.matrixmall.model.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    //获取最大范围的数据权限
    Integer getMaximumDataScope(Set<String> roles);
}
