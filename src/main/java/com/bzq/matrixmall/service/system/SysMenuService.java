package com.bzq.matrixmall.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.model.entity.system.SysMenu;
import com.bzq.matrixmall.model.form.system.MenuForm;
import com.bzq.matrixmall.model.query.system.MenuQuery;
import com.bzq.matrixmall.model.vo.system.MenuVO;
import com.bzq.matrixmall.model.vo.system.RouteVO;

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
