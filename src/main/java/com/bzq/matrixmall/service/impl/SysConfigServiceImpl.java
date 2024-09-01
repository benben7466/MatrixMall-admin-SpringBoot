package com.bzq.matrixmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.common.constant.RedisConstants;
import com.bzq.matrixmall.mapper.SysConfigMapper;
import com.bzq.matrixmall.model.entity.SysConfig;
import com.bzq.matrixmall.service.SysConfigService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Object getSystemConfig(String key) {
        if (StringUtils.isNotBlank(key)) {
            return redisTemplate.opsForHash().get(RedisConstants.SYSTEM_CONFIG_KEY, key);
        }
        return null;
    }
}
