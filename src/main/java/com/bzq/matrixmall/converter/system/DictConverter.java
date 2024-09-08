package com.bzq.matrixmall.converter.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bzq.matrixmall.model.entity.system.SysDict;
import com.bzq.matrixmall.model.form.system.DictForm;
import com.bzq.matrixmall.model.vo.system.DictPageVO;
import org.mapstruct.Mapper;

//字典 对象转换器
@Mapper(componentModel = "spring")
public interface DictConverter {
    Page<DictPageVO> toPageVo(Page<SysDict> page);

    DictForm toForm(SysDict entity);

    SysDict toEntity(DictForm entity);
}
