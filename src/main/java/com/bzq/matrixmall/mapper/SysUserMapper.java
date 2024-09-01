package com.bzq.matrixmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bzq.matrixmall.model.dto.UserAuthInfo;
import com.bzq.matrixmall.model.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

//用户持久层
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    //根据用户名获取认证信息
    UserAuthInfo getUserAuthInfo(String username);
}
