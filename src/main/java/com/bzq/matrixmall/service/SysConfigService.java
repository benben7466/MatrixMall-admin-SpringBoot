package com.bzq.matrixmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.model.entity.SysConfig;

//系统配置Service接口
public interface SysConfigService extends IService<SysConfig> {

    //获取系统配置
    Object getSystemConfig(String key);
}
