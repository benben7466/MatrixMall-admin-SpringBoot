package com.bzq.matrixmall.converter.product;

import com.bzq.matrixmall.model.entity.product.ProdCategory;
import com.bzq.matrixmall.model.entity.product.ProdProductCategory;
import com.bzq.matrixmall.model.form.product.ProdCategoryForm;
import com.bzq.matrixmall.model.vo.product.ProdCategoryVO;
import com.bzq.matrixmall.model.vo.product.ProdProductCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//转换器
@Mapper(componentModel = "spring")
public interface ProdProductCategoryConverter {

    ProdProductCategoryVO toVo(ProdProductCategory entity);


}
