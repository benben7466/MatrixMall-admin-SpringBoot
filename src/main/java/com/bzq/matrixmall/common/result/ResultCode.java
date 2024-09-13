package com.bzq.matrixmall.common.result;

//响应码枚举

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode implements IResultCode, Serializable {

    SUCCESS("10000", "success"),

    USER_ERROR("A0001", "用户端错误"),
    REPEAT_SUBMIT_ERROR("A0002", "您的请求已提交，请不要重复提交或等待片刻再尝试。"),

    USERNAME_OR_PASSWORD_ERROR("A0210", "用户名或密码错误"),
    PASSWORD_ENTER_EXCEED_LIMIT("A0211", "用户输入密码次数超限"),
    CLIENT_AUTHENTICATION_FAILED("A0212", "客户端认证失败"),
    VERIFY_CODE_TIMEOUT("A0213", "验证码已过期"),
    VERIFY_CODE_ERROR("A0214", "验证码错误"),

    TOKEN_INVALID("A0230", "token无效或已过期"),
    TOKEN_ACCESS_FORBIDDEN("A0231", "token已被禁止访问"),
    TOKEN_IN_BLACKLIST("A0232", "token已在黑名单"),

    ACCESS_UNAUTHORIZED("A0301", "访问未授权"),

    PARAM_ERROR("A0400", "用户请求参数错误"),
    RESOURCE_NOT_FOUND("A0401", "请求资源不存在"),
    PARAM_IS_NULL("A0410", "请求必填参数为空"),
    UPLOAD_FILE_EXCEED_SIZE_LIMIT("A0412", "上传文件超过系统限制"),

    SYSTEM_EXECUTION_ERROR("B0001", "系统执行出错"),

    FLOW_LIMITING("B0210", "系统限流,请稍后再试"),

    DATABASE_PRIMARY_KEY_CONFLICT("C0341", "主键冲突");

    private String code;
    private String msg;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":\"" + code + '\"' +
                ", \"msg\":\"" + msg + '\"' +
                '}';
    }

}
