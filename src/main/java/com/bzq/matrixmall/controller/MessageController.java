package com.bzq.matrixmall.controller;

import com.bzq.matrixmall.common.result.Result;
import com.bzq.matrixmall.model.vo.MessageVO;
import com.bzq.matrixmall.model.vo.RouteVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

//消息控制层
@Tag(name = "10.消息接口")
@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageController {

    @Operation(summary = "获取消息信息")
    @GetMapping("/gets")
    public Result<List<RouteVO>> gets() {
        return Result.success(Collections.emptyList());
    }
}
