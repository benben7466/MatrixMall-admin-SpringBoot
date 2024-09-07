package com.bzq.matrixmall.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.model.entity.SysDictItem;
import com.bzq.matrixmall.model.form.DictForm;
import com.bzq.matrixmall.model.vo.DictPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

//字典项 对象转换器
@Mapper(componentModel = "spring")
public interface DictItemConverter {

    @Mappings({
            @Mapping(target = "value", source = "value"),
            @Mapping(target = "label", source = "name")
    })
    Option<Long> toOption(SysDictItem dictItem);
    List<Option<Long>> toOption(List<SysDictItem> dictItems);

    DictForm.DictItem toDictItem(SysDictItem entity);
    List<DictForm.DictItem> toDictItem(List<SysDictItem> entities);

    DictForm toForm(SysDictItem entity);

    SysDictItem toEntity(DictForm.DictItem dictItems);
    List<SysDictItem> toEntity(List<DictForm.DictItem> dictItems);

    Page<DictPageVO> toPageVo(Page<SysDictItem> page);

}
