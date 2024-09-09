package com.bzq.matrixmall.model.vo.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

//商品品牌分页视图对象
@Schema(description ="商品品牌分页对象")
@Data
public class ProdBrandPageVO {
    @Schema(description="自增编号")
    private Long id;

    @Schema(description="品牌名称")
    private String brandName;

    @Schema(description="排序")
    private Integer sort;

    @Schema(description="状态（1-启用，0-禁用）")
    private Integer status;

    @Schema(description="更新时间")
    private LocalDateTime updateTime;
}
