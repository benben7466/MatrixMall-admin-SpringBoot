package com.bzq.matrixmall.model.form.product;

//分类表单对象

import com.bzq.matrixmall.common.model.KeyValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "分类表单对象")
@Data
public class ProdCategoryForm {
    @Schema(description="自增编号")
    private Long id;

    @Schema(description="父ID", example = "1000")
    @NotNull(message = "父ID不能为空")
    private Long parentId;

    @Schema(description="分类名称")
    @NotBlank(message = "分类名称不能为空")
    private String categoryName;

    @Schema(description="排序")
    private Integer sort;

    @Schema(description = "状态（1-启用，0-禁用）", example = "1")
    @Range(min = 0, max = 1, message = "状态不正确")
    private Integer status;

    @Schema(description="图标url")
    private String iconUrl;
}
