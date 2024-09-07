package com.bzq.matrixmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.model.entity.SysMenu;
import com.bzq.matrixmall.model.form.MenuForm;
import com.bzq.matrixmall.model.query.MenuQuery;
import com.bzq.matrixmall.model.vo.MenuVO;
import com.bzq.matrixmall.model.vo.RouteVO;

import java.util.List;
import java.util.Set;

public interface SysMenuService extends IService<SysMenu> {
    //获取路由列表
    List<RouteVO> listRoutes(Set<String> roles);

    //获取菜单下拉列表
    List<Option> listMenuOptions(boolean onlyParent);

    //获取菜单表格列表
    List<MenuVO> listMenus(MenuQuery queryParams);

    //新增菜单
    boolean saveMenu(MenuForm menuForm);

    //删除菜单
    boolean deleteMenu(Long id);

    //获取菜单表单数据
    MenuForm getMenuForm(Long id);
}
