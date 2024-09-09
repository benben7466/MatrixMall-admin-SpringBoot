package com.bzq.matrixmall.model.query.product;

import com.bzq.matrixmall.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

//商品信息分页查询对象
@Schema(description = "商品信息分页查询对象")
@Data
@EqualsAndHashCode(callSuper=false)
public class ProdInfoPageQuery extends BasePageQuery {
    @Schema(description = "关键字(商品名称/商品子名称/手机号)")
    private String keywords;

    @Schema(description = "品牌编号")
    private Long brandId;

    @Schema(description = "是否删除")
    private Integer isDeleted;

    @Schema(description = "创建时间范围")
    private List<String> createTime;
}
