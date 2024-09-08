package com.bzq.matrixmall.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bzq.matrixmall.model.entity.product.ProdInfo;
import org.apache.ibatis.annotations.Mapper;

//持久层
@Mapper
public interface ProdInfoMapper extends BaseMapper<ProdInfo> {
    //获取用户表单详情
    ProdInfo getProdInfoFormData(Long prodId);
}
