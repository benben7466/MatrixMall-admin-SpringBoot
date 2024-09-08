package com.bzq.matrixmall.converter.product;

import com.bzq.matrixmall.model.entity.product.ProdInfo;
import com.bzq.matrixmall.model.form.product.ProdInfoForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//转换器

@Mapper(componentModel = "spring")
public interface ProdInfoConverter {

    @Mappings({
            @Mapping(target = "createBy", expression = "java(com.bzq.matrixmall.security.util.SecurityUtils.getUserId())"),
            @Mapping(target = "updateBy", expression = "java(com.bzq.matrixmall.security.util.SecurityUtils.getUserId())")
    })
    ProdInfo toEntity(ProdInfoForm infoForm);

    ProdInfoForm toForm(ProdInfo prodInfo);
}
