package com.bzq.matrixmall.converter.product;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.model.entity.product.ProdBrand;
import com.bzq.matrixmall.model.form.product.ProdBrandForm;
import com.bzq.matrixmall.model.vo.product.ProdBrandPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
//转换器

@Mapper(componentModel = "spring")
public interface ProdBrandConverter {

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "brandName")
    })
    Option<Long> entity2Option(ProdBrand brand);
    List<Option<Long>> entities2Options(List<ProdBrand> brands);

    ProdBrand toEntity(ProdBrandForm infoForm);

    ProdBrandForm toForm(ProdBrand prodBrand);

    Page<ProdBrandPageVO> toPageVo(Page<ProdBrand> page);

}
