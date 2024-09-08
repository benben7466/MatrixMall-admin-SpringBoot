package com.bzq.matrixmall.model.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bzq.matrixmall.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

//商品信息实体，对应数据库字段
@TableName("prod_info")
@Getter
@Setter
public class ProdInfo extends BaseEntity {
    private String productName;//商品名称
    private String productNameSub;//商品子名称
    private Integer brandId;//品牌编号
    private Integer isDeleted;//是否删除
    private String specifications;//规格
    private Long unit;//单位
    private String expirationDate;//保质期
    private Long producingArea;//产地
    private String seoKeyword;//SEO关键词
    private String seoDescription;//SEO描述
    private Integer createBy;//创建人 ID
    private Integer updateBy;//更新人 ID
}
