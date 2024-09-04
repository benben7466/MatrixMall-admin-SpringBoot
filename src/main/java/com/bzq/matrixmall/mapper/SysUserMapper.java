package com.bzq.matrixmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bzq.matrixmall.model.bo.UserBO;
import com.bzq.matrixmall.model.dto.UserAuthInfo;
import com.bzq.matrixmall.model.entity.SysUser;
import com.bzq.matrixmall.model.query.UserPageQuery;
import com.bzq.matrixmall.plugin.syslog.annotation.DataPermission;
import org.apache.ibatis.annotations.Mapper;

//用户持久层
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    //根据用户名获取认证信息
    UserAuthInfo getUserAuthInfo(String username);

    //获取用户分页列表
    @DataPermission(deptAlias = "u")
    Page<UserBO> listPagedUsers(Page<UserBO> page, UserPageQuery queryParams);
}
