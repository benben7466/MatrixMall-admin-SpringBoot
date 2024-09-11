package com.bzq.matrixmall.model.query.product;

import com.bzq.matrixmall.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

//查询对象
@Schema(description = "分类查询对象")
@Data
@EqualsAndHashCode(callSuper=false)
public class ProdCategoryQuery extends BasePageQuery {
    @Schema(description = "分类名称")
    private String keywords;
}
