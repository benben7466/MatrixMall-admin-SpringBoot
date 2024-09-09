package com.bzq.matrixmall.model.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bzq.matrixmall.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//商品品牌实体，对应数据库字段
@TableName("prod_brand")
@Getter
@Setter
public class ProdBrand extends BaseEntity {
    private String brandName;//品牌名称
    private Integer sort;//排序
    private Integer status;//状态((1-正常 0-禁用)
}
