package com.bzq.matrixmall.filter;

import com.bzq.matrixmall.common.constant.RedisConstants;
import com.bzq.matrixmall.service.system.SysConfigService;
import com.bzq.matrixmall.common.result.ResultCode;
import com.bzq.matrixmall.util.IPUtils;
import com.bzq.matrixmall.util.ResponseUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

//IP限流过滤器
@Slf4j
public class RedisRateLimiterFilter extends OncePerRequestFilter {

    private final RedisTemplate<String, Object> redisTemplate;
    private final SysConfigService sysConfigService;

    public RedisRateLimiterFilter(RedisTemplate<String, Object> redisTemplate, SysConfigService sysConfigService) {
        this.redisTemplate = redisTemplate;
        this.sysConfigService = sysConfigService;
    }

    //确认是否限流方法
    //默认情况下：限制同一个IP的QPS最大为10,也可通过修改系统配置进行调整
    public boolean rateLimit(String ip) {
        String key = RedisConstants.IP_RATE_LIMITER_KEY + ip;
        Long count = redisTemplate.opsForValue().increment(key);
        if (count == null || count == 1) {
            redisTemplate.expire(key, 1, TimeUnit.SECONDS);
        }
        Object systemConfig = sysConfigService.getSystemConfig(RedisConstants.IP_QPS_THRESHOLD_LIMIT_KEY);
        long limit = 10;
        if (systemConfig != null) {
            limit = Long.parseLong(systemConfig.toString());
        } else {
            log.warn("[RedisRateLimiterFilter.rateLimit]系统配置中未配置IP请求限制QPS阈值配置,使用默认值:{},请检查配置项:{}",
                    limit, RedisConstants.IP_QPS_THRESHOLD_LIMIT_KEY);
        }
        return count != null && count > limit;
    }

    //IP限流过滤器
    //默认情况下：限制同一个IP在一秒钟内只能访问10次，可以通过修改系统配置进行调整
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String ip = IPUtils.getIpAddr(request);

        if (rateLimit(ip)) {
            ResponseUtils.writeErrMsg(response, ResultCode.FLOW_LIMITING);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
