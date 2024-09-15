package com.bzq.matrixmall.converter.product;

import com.bzq.matrixmall.model.entity.product.ProdProductImage;
import com.bzq.matrixmall.model.vo.product.ProdProductImageVO;
import org.mapstruct.Mapper;

//转换器
@Mapper(componentModel = "spring")
public interface ProdProductImageConverter {

    ProdProductImageVO toVo(ProdProductImage entity);


}
