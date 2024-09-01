package com.bzq.matrixmall.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//验证码属性
@ConfigurationProperties(prefix = "captcha")
@Data
public class CaptchaProperties {
    private String type;//验证码类型  circle-圆圈干扰验证码|gif-Gif验证码|line-干扰线验证码|shear-扭曲干扰验证码
    private int width;//验证码图片宽度
    private int height;//验证码图片高度
    private int interfereCount;//干扰线数量
    private Float textAlpha;//文本透明度
    private Long expireSeconds;//验证码过期时间，单位：秒
    private CodeProperties code;//验证码字符配置
    private FontProperties font;//验证码字体

    //验证码字符配置
    @Data
    public static class CodeProperties {
        private String type;//验证码字符类型 math-算术|random-随机字符串
        private int length;//验证码字符长度，type=算术时，表示运算位数(1:个位数 2:十位数)；type=随机字符时，表示字符个数
    }

    //验证码字体配置
    @Data
    public static class FontProperties {
        private String name;//字体名称
        private int weight;//字体样式  0-普通|1-粗体|2-斜体
        private int size;//字体大小
    }
}
