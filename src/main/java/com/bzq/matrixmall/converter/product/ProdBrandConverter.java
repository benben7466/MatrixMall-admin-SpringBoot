package com.bzq.matrixmall.converter.product;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bzq.matrixmall.model.entity.product.ProdBrand;
import com.bzq.matrixmall.model.form.product.ProdBrandForm;
import com.bzq.matrixmall.model.vo.product.ProdBrandPageVO;
import org.mapstruct.Mapper;
//转换器

@Mapper(componentModel = "spring")
public interface ProdBrandConverter {

    ProdBrand toEntity(ProdBrandForm infoForm);

    ProdBrandForm toForm(ProdBrand prodBrand);

    Page<ProdBrandPageVO> toPageVo(Page<ProdBrand> page);

}
