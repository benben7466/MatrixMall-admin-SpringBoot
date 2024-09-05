package com.bzq.matrixmall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bzq.matrixmall.model.dto.UserAuthInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.model.entity.SysUser;
import com.bzq.matrixmall.model.form.UserForm;
import com.bzq.matrixmall.model.query.UserPageQuery;
import com.bzq.matrixmall.model.vo.UserInfoVO;
import com.bzq.matrixmall.model.vo.UserPageVO;

//用户业务接口
public interface SysUserService extends IService<SysUser> {

    //新增用户
    boolean saveUser(UserForm userForm);

    //获取用户表单数据
    UserForm getUserFormData(Long userId);

    //修改用户
    boolean updateUser(Long userId, UserForm userForm);

    //删除用户
    boolean deleteUsers(String ids);

    //根据用户名获取认证信息
    UserAuthInfo getUserAuthInfo(String username);

    //获取登录用户信息
    UserInfoVO getCurrentUserInfo();

    //用户分页列表
    IPage<UserPageVO> listPagedUsers(UserPageQuery queryParams);

    //重置用户密码
    boolean resetPassword(Long userId, String password);

}
