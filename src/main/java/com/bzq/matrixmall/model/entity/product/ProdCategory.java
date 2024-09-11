package com.bzq.matrixmall.model.entity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bzq.matrixmall.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

//分类实体，对应数据库字段
@TableName("prod_category")
@Getter
@Setter
public class ProdCategory extends BaseEntity {
    private Integer parentId;//父菜单ID
    private String categoryName;//分类名称
    private String treePath;//父节点ID路径
    private Integer sort;//显示顺序
    private Integer status;//状态(1-正常 0-禁用)
    private String iconUrl;//图标url
    private Integer createBy;//创建人ID
    private Integer updateBy;//修改人ID
}
