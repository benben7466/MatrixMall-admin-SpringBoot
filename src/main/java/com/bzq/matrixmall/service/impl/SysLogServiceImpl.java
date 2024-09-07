package com.bzq.matrixmall.service.impl;

//系统日志 服务实现类

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.mapper.SysLogMapper;
import com.bzq.matrixmall.model.entity.SysLog;
import com.bzq.matrixmall.model.query.LogPageQuery;
import com.bzq.matrixmall.model.vo.LogPageVO;
import com.bzq.matrixmall.service.SysLogService;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
    //获取日志分页列表
    @Override
    public Page<LogPageVO> listPagedLogs(LogPageQuery queryParams) {
        return this.baseMapper.listPagedLogs(new Page<>(queryParams.getPageNum(), queryParams.getPageSize()), queryParams);
    }
}
