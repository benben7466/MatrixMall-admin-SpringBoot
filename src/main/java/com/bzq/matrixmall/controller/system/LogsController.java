package com.bzq.matrixmall.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bzq.matrixmall.common.result.PageResult;
import com.bzq.matrixmall.common.result.Result;
import com.bzq.matrixmall.model.query.system.LogPageQuery;
import com.bzq.matrixmall.model.vo.system.LogPageVO;
import com.bzq.matrixmall.model.vo.system.VisitStatsVO;
import com.bzq.matrixmall.service.system.SysLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

//消息控制层
@Tag(name = "11.日志接口")
@RestController
@RequestMapping("/api/v1/logs")
@RequiredArgsConstructor
public class LogsController {

    private final SysLogService logService;

    @Operation(summary = "获取访问统计信息")
    @GetMapping("/visit-stats")
    public Result<List<VisitStatsVO>> getVisitStats() {
        return Result.success(Collections.emptyList());
    }

    @Operation(summary = "日志分页列表")
    @GetMapping("/page")
    public PageResult<LogPageVO> listPagedLogs(
            LogPageQuery queryParams
    ) {
        Page<LogPageVO> result = logService.listPagedLogs(queryParams);
        return PageResult.success(result);
    }

}
