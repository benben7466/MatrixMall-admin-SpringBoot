package com.bzq.matrixmall.model.entity.system;

import com.baomidou.mybatisplus.annotation.*;
import com.bzq.matrixmall.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

//系统配置，对应数据库字段
@Schema(description = "系统配置")
@TableName("sys_config")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysConfig extends BaseEntity {

    @Schema(description = "配置名称")
    private String configName;

    @Schema(description = "配置键")
    private String configKey;

    @Schema(description = "配置值")
    private String configValue;

    @Schema(description = "描述、备注")
    private String remark;

    @Schema(description = "创建人ID")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @Schema(description = "更新人ID")
    @TableField(fill = FieldFill.UPDATE)
    private Long updateBy;

    @TableLogic(value = "0", delval = "1")
    private Integer isDeleted;

}
