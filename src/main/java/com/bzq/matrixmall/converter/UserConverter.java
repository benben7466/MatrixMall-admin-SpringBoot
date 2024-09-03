package com.bzq.matrixmall.converter;

//用户对象转换器

import com.bzq.matrixmall.model.entity.SysUser;
import com.bzq.matrixmall.model.vo.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserConverter {


    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    UserInfoVO toUserInfoVo(SysUser entity);
}
