package com.bzq.matrixmall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bzq.matrixmall.common.result.PageResult;
import com.bzq.matrixmall.common.result.Result;
import com.bzq.matrixmall.enums.LogModuleEnum;
import com.bzq.matrixmall.model.form.UserForm;
import com.bzq.matrixmall.model.query.UserPageQuery;
import com.bzq.matrixmall.model.vo.UserInfoVO;
import com.bzq.matrixmall.model.vo.UserPageVO;
import com.bzq.matrixmall.plugin.norepeat.annotation.PreventRepeatSubmit;
import com.bzq.matrixmall.plugin.syslog.annotation.LogAnnotation;
import com.bzq.matrixmall.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//用户控制层
@Tag(name = "02.用户接口")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService userService;

    @Operation(summary = "新增用户")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('sys:user:add')")
    @PreventRepeatSubmit
    public Result<?> saveUser(
            @RequestBody @Valid UserForm userForm
    ) {
        boolean result = userService.saveUser(userForm);
        return Result.judge(result);
    }

    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/me")
    public Result<UserInfoVO> getCurrentUserInfo() {
        UserInfoVO userInfoVO = userService.getCurrentUserInfo();
        return Result.success(userInfoVO);
    }

    @Operation(summary = "用户分页列表")
    @GetMapping("/page")
    @LogAnnotation(value = "用户分页列表", module = LogModuleEnum.USER)
    public PageResult<UserPageVO> listPagedUsers(UserPageQuery queryParams) {
        IPage<UserPageVO> result = userService.listPagedUsers(queryParams);
        return PageResult.success(result);
    }
}
