package com.bzq.matrixmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.model.entity.SysDict;

import java.util.List;

//数据字典业务接口
public interface SysDictService extends IService<SysDict> {

    //获取字典的数据项
    List<Option<Long>> listDictItemsByCode(String code);
}
