package com.bzq.matrixmall.model.query;

import com.bzq.matrixmall.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

//用户分页查询对象
@Schema(description ="用户分页查询对象")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserPageQuery extends BasePageQuery {
    @Schema(description="关键字(用户名/昵称/手机号)")
    private String keywords;

    @Schema(description="用户状态")
    private Integer status;

    @Schema(description="部门ID")
    private Long deptId;

    @Schema(description="创建时间范围")
    private List<String> createTime;
}
