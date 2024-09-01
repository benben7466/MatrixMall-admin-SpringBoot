package com.bzq.matrixmall.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
    private List<String> ignoreUrls;//白名单 URL 集合
    private JwtProperty jwt;//JWT 配置

    //JWT 配置
    @Data
    public static class JwtProperty {
        private String key;//JWT 密钥
        private Long ttl;//JWT 过期时间

    }
}