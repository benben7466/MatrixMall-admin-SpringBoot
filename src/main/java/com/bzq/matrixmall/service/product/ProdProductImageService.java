package com.bzq.matrixmall.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.common.model.KeyValue;
import com.bzq.matrixmall.model.entity.product.ProdProductCategory;
import com.bzq.matrixmall.model.entity.product.ProdProductImage;

import java.util.List;

//商品图片
public interface ProdProductImageService extends IService<ProdProductImage> {
    //保存
    void saveProductImage(Long productId, List<String> picUrls);

    //删除
    void deleteProductImage(Long productId);

    //取得商品图片
    List<String> getProductImageData(Long prodId);

    //通过商品ID集合，取得图片集合
    List<ProdProductImage> getProductImageByPids(List<Long> productIds);
}
