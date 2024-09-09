package com.bzq.matrixmall.model.form.product;

//商品品牌表单对象

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Schema(description = "商品品牌表单对象")
@Data
public class ProdBrandForm {
    @Schema(description="自增编号")
    private Long id;

    @Schema(description="品牌名称")
    @NotBlank(message = "品牌名称不能为空")
    private String brandName;

    @Schema(description="排序")
    private String sort;

    @Schema(description = "状态（1-启用，0-禁用）", example = "1")
    @Range(min = 0, max = 1, message = "状态不正确")
    private Integer status;

    @Schema(description="更新时间")
    private LocalDateTime updateTime;
}
