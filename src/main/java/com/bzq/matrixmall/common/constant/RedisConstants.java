package com.bzq.matrixmall.common.constant;

//Redis Key常量
public interface RedisConstants {

    //系统配置Redis-key
    String SYSTEM_CONFIG_KEY = "system:config";

    //IP限流Redis-key
    String IP_RATE_LIMITER_KEY = "ip:rate:limiter:";

    //单个IP请求的最大每秒查询数（QPS）阈值Key
    String IP_QPS_THRESHOLD_LIMIT_KEY = "IP_QPS_THRESHOLD_LIMIT";
}
