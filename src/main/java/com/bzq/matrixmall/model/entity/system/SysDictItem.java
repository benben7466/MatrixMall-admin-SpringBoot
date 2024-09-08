package com.bzq.matrixmall.model.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

//字典项实体
@Data
public class SysDictItem implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;//主键

    private Long dictId;//字典类ID
    private String name;//字典项名称
    private String value;//字典项值
    private Integer sort;//排序
    private Integer status;//状态（1-正常，0-禁用）
    private String remark;//备注
}
