package com.bzq.matrixmall.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.model.entity.system.SysLog;
import com.bzq.matrixmall.model.query.system.LogPageQuery;
import com.bzq.matrixmall.model.vo.system.LogPageVO;

//系统日志 服务接口
public interface SysLogService extends IService<SysLog> {
    //获取日志分页列表
    Page<LogPageVO> listPagedLogs(LogPageQuery queryParams);
}
