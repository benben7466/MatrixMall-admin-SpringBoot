package com.bzq.matrixmall.model.query.system;

import com.bzq.matrixmall.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description ="字典数据项分页查询对象")
@Data
@EqualsAndHashCode(callSuper=false)
public class DictPageQuery extends BasePageQuery {
    @Schema(description="关键字(字典项名称)")
    private String keywords;

    @Schema(description="字典编码")
    private String typeCode;
}
