package com.bzq.matrixmall.common.constant;

//安全常量
public interface SecurityConstants
{
    String CAPTCHA_CODE_PREFIX = "captcha_code:";//验证码缓存前缀
    String LOGIN_PATH = "/api/v1/auth/login";//登录路径
    String JWT_TOKEN_PREFIX = "Bearer ";//JWT Token 前缀
    String BLACKLIST_TOKEN_PREFIX = "token:blacklist:";//黑名单Token缓存前缀
    String ROLE_PERMS_PREFIX = "role_perms:";//角色和权限缓存前缀
}
