package com.bzq.matrixmall.model.vo.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;

//分类视图对象
@Schema(description ="分类视图对象")
@Data
public class ProdCategoryVO {
    @Schema(description="分类ID")
    private Long id;

    @Schema(description="父菜单ID")
    private Long parentId;

    @Schema(description="分类名称")
    private String categoryName;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态(1:启用；0:禁用)")
    private Integer status;

    @Schema(description="图标url")
    private String iconUrl;

    @Schema(description = "子分类")
    private List<ProdCategoryVO> children;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;
    @Schema(description = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateTime;
}
