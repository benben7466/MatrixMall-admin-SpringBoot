package com.bzq.matrixmall.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bzq.matrixmall.model.bo.product.ProductCategoryBO;
import com.bzq.matrixmall.model.entity.product.ProdCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProdCategoryMapper extends BaseMapper<ProdCategory> {

    //通过三级分类ID，取得分类名称（含1，2，3级）
    List<ProductCategoryBO> getCategoryNameListByProductIds(List<Long> productIds);
}