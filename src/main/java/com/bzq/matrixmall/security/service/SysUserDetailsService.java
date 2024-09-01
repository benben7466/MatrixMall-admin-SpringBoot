package com.bzq.matrixmall.security.service;

import com.bzq.matrixmall.model.dto.UserAuthInfo;
import com.bzq.matrixmall.security.model.SysUserDetails;
import com.bzq.matrixmall.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//系统用户认证 DetailsService
@Service
@RequiredArgsConstructor
@Slf4j
public class SysUserDetailsService implements UserDetailsService {

    private final SysUserService sysUserService;

    //根据用户名获取用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserAuthInfo userAuthInfo = sysUserService.getUserAuthInfo(username);
            if (userAuthInfo == null) {
                throw new UsernameNotFoundException(username);
            }
            return new SysUserDetails(userAuthInfo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("认证异常:{}", e.getMessage());// 记录异常日志
            throw e;// 抛出异常
        }
    }
}
