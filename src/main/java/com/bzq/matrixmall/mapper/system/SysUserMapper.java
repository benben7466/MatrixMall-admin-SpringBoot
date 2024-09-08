package com.bzq.matrixmall.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bzq.matrixmall.model.bo.system.UserBO;
import com.bzq.matrixmall.model.dto.system.UserAuthInfo;
import com.bzq.matrixmall.model.dto.system.UserExportDTO;
import com.bzq.matrixmall.model.entity.system.SysUser;
import com.bzq.matrixmall.model.form.system.UserForm;
import com.bzq.matrixmall.model.query.system.UserPageQuery;
import com.bzq.matrixmall.plugin.syslog.annotation.DataPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//用户持久层
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    //根据用户名获取认证信息
    UserAuthInfo getUserAuthInfo(String username);

    //获取用户分页列表
    @DataPermission(deptAlias = "u")
    Page<UserBO> listPagedUsers(Page<UserBO> page, UserPageQuery queryParams);

    //获取用户表单详情
    UserForm getUserFormData(Long userId);

    //获取导出用户列表
    @DataPermission(deptAlias = "u")
    List<UserExportDTO> listExportUsers(UserPageQuery queryParams);
}
