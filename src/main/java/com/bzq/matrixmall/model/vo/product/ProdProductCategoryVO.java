package com.bzq.matrixmall.model.vo.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

//商品与分类视图对象
@Schema(description ="商品与分类视图对象")
@Data
public class ProdProductCategoryVO {
    @Schema(description="商品ID")
    private Long productId;

    @Schema(description="分类ID")
    private Long categoryId;
}
