package com.bzq.matrixmall.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bzq.matrixmall.model.entity.system.SysLog;
import com.bzq.matrixmall.model.query.system.LogPageQuery;
import com.bzq.matrixmall.model.vo.system.LogPageVO;
import org.apache.ibatis.annotations.Mapper;

//系统日志 数据库访问层
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {
    //获取日志分页列表
    Page<LogPageVO> listPagedLogs(Page<LogPageVO> page, LogPageQuery queryParams);
}
