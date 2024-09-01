package com.bzq.matrixmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bzq.matrixmall.model.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;

//系统配置 访问层
@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfig> {
}
