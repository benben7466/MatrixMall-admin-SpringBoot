package com.bzq.matrixmall.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bzq.matrixmall.model.entity.SysDict;
import com.bzq.matrixmall.model.form.DictForm;
import com.bzq.matrixmall.model.vo.DictPageVO;
import org.mapstruct.Mapper;

//字典 对象转换器
@Mapper(componentModel = "spring")
public interface DictConverter {
    Page<DictPageVO> toPageVo(Page<SysDict> page);

    DictForm toForm(SysDict entity);

    SysDict toEntity(DictForm entity);
}
