package com.bzq.matrixmall.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.common.constant.SystemConstants;
import com.bzq.matrixmall.enums.MenuTypeEnum;
import com.bzq.matrixmall.enums.StatusEnum;
import com.bzq.matrixmall.mapper.SysMenuMapper;
import com.bzq.matrixmall.model.bo.RouteBO;
import com.bzq.matrixmall.model.entity.SysMenu;
import com.bzq.matrixmall.model.vo.RouteVO;
import com.bzq.matrixmall.service.SysMenuService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

//菜单业务实现类
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    //获取菜单路由列表
    @Override
    public List<RouteVO> listRoutes(Set<String> roles) {
        if (CollectionUtil.isEmpty(roles)) {
            return Collections.emptyList();
        }

        List<RouteBO> menuList = this.baseMapper.listRoutes(roles);
        return buildRoutes(SystemConstants.ROOT_NODE_ID, menuList);
    }

    //递归生成菜单路由层级列表
    private List<RouteVO> buildRoutes(Long parentId, List<RouteBO> menuList) {
        List<RouteVO> routeList = new ArrayList<>();

        for (RouteBO menu : menuList) {
            if (menu.getParentId().equals(parentId)) {
                RouteVO routeVO = toRouteVo(menu);
                List<RouteVO> children = buildRoutes(menu.getId(), menuList);

                if (!children.isEmpty()) {
                    routeVO.setChildren(children);
                }
                routeList.add(routeVO);
            }
        }

        return routeList;
    }

    //根据RouteBO创建RouteVO
    private RouteVO toRouteVo(RouteBO routeBO) {
        RouteVO routeVO = new RouteVO();

        // 获取路由名称
        String routeName = routeBO.getRouteName();
        if (StrUtil.isBlank(routeName)) {
            routeName = StringUtils.capitalize(StrUtil.toCamelCase(routeBO.getRoutePath(), '-'));// 路由 name 需要驼峰，首字母大写
        }
        routeVO.setName(routeName);// 根据name路由跳转 this.$router.push({name:xxx})
        routeVO.setPath(routeBO.getRoutePath()); // 根据path路由跳转 this.$router.push({path:xxx})
        routeVO.setRedirect(routeBO.getRedirect());
        routeVO.setComponent(routeBO.getComponent());

        RouteVO.Meta meta = new RouteVO.Meta();
        meta.setTitle(routeBO.getName());
        meta.setIcon(routeBO.getIcon());
        meta.setHidden(StatusEnum.DISABLE.getValue().equals(routeBO.getVisible()));

        if (MenuTypeEnum.MENU.equals(routeBO.getType()) && ObjectUtil.equals(routeBO.getKeepAlive(), 1)) {// 【菜单】是否开启页面缓存
            meta.setKeepAlive(true);
        }
        meta.setAlwaysShow(ObjectUtil.equals(routeBO.getAlwaysShow(), 1));

        String paramsJson = routeBO.getParams();
        // 将 JSON 字符串转换为 Map<String, String>
        if (StrUtil.isNotBlank(paramsJson)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Map<String, String> paramMap = objectMapper.readValue(paramsJson, new TypeReference<>() {});
                meta.setParams(paramMap);
            } catch (Exception e) {
                throw new RuntimeException("解析参数失败", e);
            }
        }
        routeVO.setMeta(meta);

        return routeVO;
    }

}
