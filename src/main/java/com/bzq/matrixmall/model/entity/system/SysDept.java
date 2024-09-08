package com.bzq.matrixmall.model.entity.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bzq.matrixmall.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

//部门实体，对应数据库字段
@TableName("sys_dept")
@Getter
@Setter
public class SysDept extends BaseEntity {
    private String name;//部门名称
    private String code;//部门编码
    private Long parentId;//父节点id
    private String treePath;//父节点id路径
    private Integer sort;//显示顺序
    private Integer status;//状态(1-正常 0-禁用)
    private Long createBy;//创建人 ID
    private Long updateBy;//更新人 ID
}
