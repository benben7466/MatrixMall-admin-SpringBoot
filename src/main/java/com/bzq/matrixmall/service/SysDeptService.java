package com.bzq.matrixmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.model.entity.SysDept;

import java.util.List;

//部门业务接口
public interface SysDeptService extends IService<SysDept> {
    //部门树形下拉选项
    List<Option<Long>> listDeptOptions();
}
