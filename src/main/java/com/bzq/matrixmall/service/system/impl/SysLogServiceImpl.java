package com.bzq.matrixmall.service.system.impl;

//系统日志 服务实现类

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.mapper.system.SysLogMapper;
import com.bzq.matrixmall.model.entity.system.SysLog;
import com.bzq.matrixmall.model.query.system.LogPageQuery;
import com.bzq.matrixmall.model.vo.system.LogPageVO;
import com.bzq.matrixmall.service.system.SysLogService;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
    //获取日志分页列表
    @Override
    public Page<LogPageVO> listPagedLogs(LogPageQuery queryParams) {
        return this.baseMapper.listPagedLogs(new Page<>(queryParams.getPageNum(), queryParams.getPageSize()), queryParams);
    }
}
