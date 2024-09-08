package com.bzq.matrixmall.converter.system;

//用户对象转换器

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bzq.matrixmall.model.bo.system.UserBO;
import com.bzq.matrixmall.model.dto.system.UserImportDTO;
import com.bzq.matrixmall.model.entity.system.SysUser;
import com.bzq.matrixmall.model.form.system.UserForm;
import com.bzq.matrixmall.model.vo.system.UserInfoVO;
import com.bzq.matrixmall.model.vo.system.UserPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    UserInfoVO toUserInfoVo(SysUser entity);

    @Mappings({
            @Mapping(target = "genderLabel", expression = "java(com.bzq.matrixmall.common.base.IBaseEnum.getLabelByValue(bo.getGender(), com.bzq.matrixmall.enums.GenderEnum.class))")
    })
    UserPageVO toPageVo(UserBO bo);
    Page<UserPageVO> toPageVo(Page<UserBO> bo);

    UserForm toForm(SysUser entity);

    @InheritInverseConfiguration(name = "toForm")
    SysUser toEntity(UserForm entity);

    SysUser toEntity(UserImportDTO vo);


}
