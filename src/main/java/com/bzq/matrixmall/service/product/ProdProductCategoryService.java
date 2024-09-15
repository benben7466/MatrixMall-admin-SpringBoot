package com.bzq.matrixmall.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.common.model.KeyValue;
import com.bzq.matrixmall.common.model.KeyValueLong;
import com.bzq.matrixmall.model.entity.product.ProdProductCategory;

import java.util.List;

//商品与分类
public interface ProdProductCategoryService  extends IService<ProdProductCategory> {
    //保存
    void saveProductCategory(Long productId, List<Long> categoryIds);

    //删除
    void deleteProductCategory(Long productId);

    //商品与分类数据
    List<KeyValue> getProdProductCategoryData(Long prodId);
}
