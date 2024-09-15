package com.bzq.matrixmall.model.bo.product;

import lombok.Data;

import java.util.Set;

@Data
public class ProductCategoryBO {
    private Long productId;//商品编码
    private String categoryNameTree;//分类名称树
}
