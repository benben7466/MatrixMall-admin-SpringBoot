package com.bzq.matrixmall.model.vo.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description ="商品图片视图对象")
@Data
public class ProdProductImageVO {
    @Schema(description="商品ID")
    private Long productId;

    @Schema(description="图片Url")
    private String imageUrl;

    @Schema(description="图片宽")
    private Integer width;

    @Schema(description="图片高")
    private Integer height;
}
