package com.bzq.matrixmall.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.model.entity.SysRole;
import com.bzq.matrixmall.model.form.RoleForm;
import com.bzq.matrixmall.model.vo.RolePageVO;
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

    Page<RolePageVO> toPageVo(Page<SysRole> page);

    SysRole toEntity(RoleForm roleForm);
    RoleForm toForm(SysRole entity);

}
