package com.bzq.matrixmall.model.entity.system;

import com.baomidou.mybatisplus.annotation.*;
import com.bzq.matrixmall.enums.MenuTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//菜单实体，对应数据库字段
@Getter
@Setter
public class SysMenu {

    @TableId(type = IdType.AUTO)
    private Long id;//菜单ID
    private Long parentId;//父菜单ID
    private String name;//菜单名称
    private MenuTypeEnum type;//菜单类型(1-菜单；2-目录；3-外链；4-按钮权限)
    private String routeName;//路由名称（Vue Router 中定义的路由名称）
    private String routePath;//路由路径（Vue Router 中定义的 URL 路径）
    private String component;//组件路径(vue页面完整路径)
    private String perm;//权限标识
    private Integer visible;//显示状态(1:显示;0:隐藏)
    private Integer sort;//排序
    private String icon;//菜单图标
    private String redirect;//跳转路径
    private String treePath;//父节点路径，以英文逗号(,)分割
    private Integer keepAlive;//【菜单】是否开启页面缓存(1:开启;0:关闭)
    private Integer alwaysShow;//【目录】只有一个子路由是否始终显示(1:是 0:否)

    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private String params;//路由参数

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;//更新时间
}
