package com.bzq.matrixmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bzq.matrixmall.model.bo.RouteBO;
import com.bzq.matrixmall.model.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

//菜单访问层
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    //获取菜单路由列表
    List<RouteBO> listRoutes(Set<String> roles);
}
