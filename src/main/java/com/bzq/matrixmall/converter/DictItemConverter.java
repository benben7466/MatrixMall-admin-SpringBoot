package com.bzq.matrixmall.converter;

import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.model.entity.SysDictItem;
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

}
