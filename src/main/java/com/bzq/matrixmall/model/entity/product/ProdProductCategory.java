package com.bzq.matrixmall.model.entity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@TableName("prod_product_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdProductCategory  implements Serializable {
    //主键ID
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long productId;//商品ID
    private Long categoryId;//分类ID

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    public ProdProductCategory(Long productId, Long categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }
}
