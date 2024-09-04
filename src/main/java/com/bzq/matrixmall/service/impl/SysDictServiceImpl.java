package com.bzq.matrixmall.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.converter.DictItemConverter;
import com.bzq.matrixmall.mapper.SysDictMapper;
import com.bzq.matrixmall.model.entity.SysDict;
import com.bzq.matrixmall.model.entity.SysDictItem;
import com.bzq.matrixmall.service.SysDictItemService;
import com.bzq.matrixmall.service.SysDictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//数据字典业务实现类
@Service
@RequiredArgsConstructor
public class SysDictServiceImpl  extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    private final SysDictItemService dictItemService;
    private final DictItemConverter dictItemConverter;

    //获取字典的数据项
    @Override
    public List<Option<Long>> listDictItemsByCode(String code) {
        // 根据字典编码获取字典ID
        SysDict dict = this.getOne(new LambdaQueryWrapper<SysDict>()
                .eq(SysDict::getCode, code)
                .select(SysDict::getId)
                .last("limit 1")
        );

        // 如果字典不存在，则返回空集合
        if (dict == null) {
            return CollectionUtil.newArrayList();
        }

        // 获取字典项
        List<SysDictItem> dictItems = dictItemService.list(
                new LambdaQueryWrapper<SysDictItem>()
                        .eq(SysDictItem::getDictId, dict.getId())
        );

        // 转换为 Option
        return dictItemConverter.toOption(dictItems);
    }
}
