package com.bzq.matrixmall.service;

import com.bzq.matrixmall.model.dto.UserAuthInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.model.entity.SysUser;

//用户业务接口
public interface SysUserService extends IService<SysUser> {
    //根据用户名获取认证信息
    UserAuthInfo getUserAuthInfo(String username);
}
