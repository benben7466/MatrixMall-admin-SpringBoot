package com.bzq.matrixmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.model.entity.SysMenu;
import com.bzq.matrixmall.model.vo.RouteVO;

import java.util.List;
import java.util.Set;

public interface SysMenuService extends IService<SysMenu> {
    //获取路由列表
    List<RouteVO> listRoutes(Set<String> roles);
}
