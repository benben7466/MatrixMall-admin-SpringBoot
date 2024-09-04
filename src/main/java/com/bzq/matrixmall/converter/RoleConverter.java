package com.bzq.matrixmall.converter;

import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.model.entity.SysRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

//角色对象转换器
@Mapper(componentModel = "spring")
public interface RoleConverter {

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option<Long> entity2Option(SysRole role);

    List<Option<Long>> entities2Options(List<SysRole> roles);
}
