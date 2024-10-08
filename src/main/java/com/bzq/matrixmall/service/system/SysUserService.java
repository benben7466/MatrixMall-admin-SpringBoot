package com.bzq.matrixmall.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bzq.matrixmall.model.dto.system.UserAuthInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.model.dto.system.UserExportDTO;
import com.bzq.matrixmall.model.entity.system.SysUser;
import com.bzq.matrixmall.model.form.system.UserForm;
import com.bzq.matrixmall.model.query.system.UserPageQuery;
import com.bzq.matrixmall.model.vo.system.UserInfoVO;
import com.bzq.matrixmall.model.vo.system.UserPageVO;

import java.util.List;

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

    //获取导出用户列表
    List<UserExportDTO> listExportUsers(UserPageQuery queryParams);
}
