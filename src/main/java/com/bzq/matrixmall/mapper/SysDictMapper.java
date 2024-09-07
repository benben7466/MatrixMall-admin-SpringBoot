package com.bzq.matrixmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bzq.matrixmall.model.entity.SysDict;
import com.bzq.matrixmall.model.query.DictPageQuery;
import com.bzq.matrixmall.model.vo.DictPageVO;
import org.apache.ibatis.annotations.Mapper;

//字典 访问层
@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {

    //字典分页列表
    Page<DictPageVO> getDictPage(Page<Object> objectPage, DictPageQuery queryParams);
}
