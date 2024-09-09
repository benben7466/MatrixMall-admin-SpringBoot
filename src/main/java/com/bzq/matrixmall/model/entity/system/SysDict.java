package com.bzq.matrixmall.model.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bzq.matrixmall.common.base.BaseEntity;
import com.bzq.matrixmall.model.vo.system.DictPageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

//字典实体，对应数据库字段
@TableName("sys_dict")
@Data
@EqualsAndHashCode(callSuper=false)
public class SysDict extends BaseEntity {
    private String name;//类型名称
    private String code;//类型编码
    private Integer status;//状态(0:正常;1:禁用)
    private String remark;//备注
}