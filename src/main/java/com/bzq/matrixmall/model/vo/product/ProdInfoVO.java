package com.bzq.matrixmall.model.vo.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

//商品信息分页视图对象
@Schema(description ="商品信息分页对象")
@Data
public class ProdInfoVO {
    @Schema(description="自增编号")
    private Long id;

    @Schema(description="商品名称")
    private String productName;

    @Schema(description="商品子名称")
    private String productNameSub;

    @Schema(description="品牌编号")
    private Integer brandId;

    @Schema(description="是否删除")
    private Integer isDeleted;

    @Schema(description="规格")
    private String specifications;

    @Schema(description="单位")
    private Long unit;

    @Schema(description="保质期")
    private String expirationDate;

    @Schema(description="产地")
    private Long producingArea;

    @Schema(description="SEO关键词")
    private String seoKeyword;

    @Schema(description="SEO描述")
    private String seoDescription;

    @Schema(description="更新时间")
    private LocalDateTime updateTime;
}
