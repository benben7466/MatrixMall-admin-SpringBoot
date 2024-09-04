package com.bzq.matrixmall.model.entity;

import com.bzq.matrixmall.common.base.BaseEntity;
import lombok.Data;

//字典实体，对应数据库字段

@Data
public class SysDict extends BaseEntity {
    private String name;//类型名称
    private String code;//类型编码
    private Integer status;//状态(0:正常;1:禁用)
    private String remark;//备注
}