package com.bzq.matrixmall.config;

import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.core.collection.CollectionUtil;
import com.bzq.matrixmall.common.constant.SecurityConstants;
import com.bzq.matrixmall.config.property.SecurityProperties;
import com.bzq.matrixmall.filter.CaptchaValidationFilter;
import com.bzq.matrixmall.filter.JwtValidationFilter;
import com.bzq.matrixmall.filter.RedisRateLimiterFilter;
import com.bzq.matrixmall.security.exception.MyAccessDeniedHandler;
import com.bzq.matrixmall.security.exception.MyAuthenticationEntryPoint;
import com.bzq.matrixmall.service.system.SysConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyAuthenticationEntryPoint authenticationEntryPoint;
    private final MyAccessDeniedHandler accessDeniedHandler;
    private final RedisTemplate<String, Object> redisTemplate;
    private final CodeGenerator codeGenerator;
    private final SecurityProperties securityProperties;
    private final SysConfigService sysConfigService;

    //安全过滤器链
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests(requestMatcherRegistry ->
                        requestMatcherRegistry.requestMatchers(SecurityConstants.LOGIN_PATH).permitAll() //指定对于特定的登录路径（由 SecurityConstants.LOGIN_PATH 定义），允许所有用户访问，即无需进行认证和授权就可以访问该路径
                                .anyRequest().authenticated() //表示对于任何其他请求，都要求用户进行认证（即用户必须是已登录状态）
                )
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> //用于配置异常处理
                        httpSecurityExceptionHandlingConfigurer
                                .authenticationEntryPoint(authenticationEntryPoint) //设置认证入口点，当用户未认证但尝试访问需要认证的资源时，会由这个入口点来处理
                                .accessDeniedHandler(accessDeniedHandler) //设置访问拒绝处理器，当已认证用户没有足够的权限访问某个资源时，由这个处理器来处理
                )
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //设置会话创建策略为无状态
                .csrf(AbstractHttpConfigurer::disable) //禁用了跨站请求伪造（CSRF）防护
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) //禁用了框架选项

        ;

        // 限流过滤器
        http.addFilterBefore(new RedisRateLimiterFilter(redisTemplate, sysConfigService), UsernamePasswordAuthenticationFilter.class);
        // 验证码校验过滤器
        http.addFilterBefore(new CaptchaValidationFilter(redisTemplate, codeGenerator), UsernamePasswordAuthenticationFilter.class);
        // JWT 校验过滤器
        http.addFilterBefore(new JwtValidationFilter(redisTemplate, securityProperties.getJwt().getKey()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //不走过滤器链的放行配置
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> {
            if (CollectionUtil.isNotEmpty(securityProperties.getIgnoreUrls())) {
                web.ignoring().requestMatchers(securityProperties.getIgnoreUrls().toArray(new String[0]));
            }
        };
    }

    //AuthenticationManager 手动注入
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
