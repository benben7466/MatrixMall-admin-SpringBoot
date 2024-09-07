package com.bzq.matrixmall.converter;

import com.bzq.matrixmall.model.entity.SysMenu;
import com.bzq.matrixmall.model.form.MenuForm;
import com.bzq.matrixmall.model.vo.MenuVO;
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
