package com.bzq.matrixmall.model.form.product;

//商品信息表单对象

import com.bzq.matrixmall.common.model.KeyValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "商品信息表单对象")
@Data
public class ProdInfoForm {
    @Schema(description="自增编号")
    private Long id;

    @Schema(description="商品名称")
    @NotBlank(message = "商品名称不能为空")
    private String productName;

    @Schema(description="商品子名称")
    @NotBlank(message = "商品子名称不能为空")
    private String productNameSub;

    @Schema(description="品牌编号")
    private Integer brandId;

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

    @Schema(description = "分类ID列表")
    private List<KeyValue> categoryIds;

    @Schema(description = "商品图片列表")
    private List<String> picUrls;
}
