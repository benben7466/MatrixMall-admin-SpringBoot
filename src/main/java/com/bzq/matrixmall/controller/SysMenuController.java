package com.bzq.matrixmall.controller;

import com.bzq.matrixmall.common.result.Result;
import com.bzq.matrixmall.model.vo.RouteVO;
import com.bzq.matrixmall.security.util.SecurityUtils;
import com.bzq.matrixmall.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

//菜单控制层

@Tag(name = "04.菜单接口")
@RestController
@RequestMapping("/api/v1/menus")
@RequiredArgsConstructor
public class SysMenuController {

    private final SysMenuService menuService;

    @Operation(summary = "菜单路由列表")
    @GetMapping("/routes")
    public Result<List<RouteVO>> listRoutes() {
        Set<String> roles = SecurityUtils.getRoles();
        List<RouteVO> routeList = menuService.listRoutes(roles);
        return Result.success(routeList);
    }

}
