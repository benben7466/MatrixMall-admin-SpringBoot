package com.bzq.matrixmall.model.entity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("prod_product_image")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdProductImage implements Serializable {
    //主键ID
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long productId;//商品ID
    private String imageUrl;//图片Url
    private Integer width;//图片宽
    private Integer height;//图片高

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public ProdProductImage(Long productId, String row, int width, int height) {
        this.productId = productId;
        this.imageUrl = row;
        this.width = width;
        this.height = height;
    }
}
