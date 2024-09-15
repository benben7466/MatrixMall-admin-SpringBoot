package com.bzq.matrixmall.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bzq.matrixmall.model.entity.product.ProdProductCategory;
import com.bzq.matrixmall.model.entity.system.SysDictItem;
import org.apache.ibatis.annotations.Mapper;

//商品与分类 访问层
@Mapper
public interface ProdProductCategoryMapper extends BaseMapper<ProdProductCategory> {

}

