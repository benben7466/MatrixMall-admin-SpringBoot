package com.bzq.matrixmall.service.system;

import com.bzq.matrixmall.model.dto.system.CaptchaResult;
import com.bzq.matrixmall.model.dto.system.LoginResult;

//认证服务接口
public interface AuthService {

    //获取验证码
    CaptchaResult getCaptcha();

    //登录
    LoginResult login(String username, String password);
}
