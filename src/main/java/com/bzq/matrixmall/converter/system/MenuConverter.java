package com.bzq.matrixmall.converter.system;

import com.bzq.matrixmall.model.entity.system.SysMenu;
import com.bzq.matrixmall.model.form.system.MenuForm;
import com.bzq.matrixmall.model.vo.system.MenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//菜单对象转换器
@Mapper(componentModel = "spring")
public interface MenuConverter {
    MenuVO toVo(SysMenu entity);

    @Mapping(target = "params", ignore = true)
    MenuForm toForm(SysMenu entity);

    @Mapping(target = "params", ignore = true)
    SysMenu toEntity(MenuForm menuForm);
}
