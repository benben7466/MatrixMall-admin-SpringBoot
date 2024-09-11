package com.bzq.matrixmall.converter.product;

import com.bzq.matrixmall.model.entity.product.ProdCategory;
import com.bzq.matrixmall.model.form.product.ProdCategoryForm;
import com.bzq.matrixmall.model.vo.product.ProdCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//转换器
@Mapper(componentModel = "spring")
public interface ProdCategoryConverter {
    ProdCategoryForm toForm(ProdCategory entity);

    ProdCategoryVO toVo(ProdCategory entity);

    @Mappings({
            @Mapping(target = "createBy", expression = "java(com.bzq.matrixmall.security.util.SecurityUtils.getUserId())"),
            @Mapping(target = "updateBy", expression = "java(com.bzq.matrixmall.security.util.SecurityUtils.getUserId())")
    })
    ProdCategory toEntity(ProdCategoryForm formData);
}
