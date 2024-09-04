package com.bzq.matrixmall.converter;

//用户对象转换器

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bzq.matrixmall.model.bo.UserBO;
import com.bzq.matrixmall.model.entity.SysUser;
import com.bzq.matrixmall.model.vo.UserInfoVO;
import com.bzq.matrixmall.model.vo.UserPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    UserInfoVO toUserInfoVo(SysUser entity);

    Page<UserPageVO> toPageVo(Page<UserBO> bo);
}
