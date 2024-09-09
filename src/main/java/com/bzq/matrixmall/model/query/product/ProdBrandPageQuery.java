package com.bzq.matrixmall.model.query.product;

import com.bzq.matrixmall.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

//商品品牌分页查询对象
@Schema(description = "商品品牌分页查询对象")
@Data
@EqualsAndHashCode(callSuper=false)
public class ProdBrandPageQuery extends BasePageQuery {
    @Schema(description = "品牌名称")
    private String keywords;
}
