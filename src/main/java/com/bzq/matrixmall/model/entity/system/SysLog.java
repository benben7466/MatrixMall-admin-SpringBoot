package com.bzq.matrixmall.model.entity.system;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bzq.matrixmall.enums.LogModuleEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

//系统日志 实体类
@Data
public class SysLog implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;//主键

    private LogModuleEnum module;//日志模块
    private String content;//日志内容
    private String requestUri;//请求路径
    private String ip;//IP 地址
    private String province;//省份
    private String city;//城市
    private String browser;//浏览器
    private String browserVersion;//浏览器版本
    private String os;//终端系统
    private Long executionTime;//执行时间(毫秒)
    private Long createBy;//创建人ID

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;//创建时间
}
