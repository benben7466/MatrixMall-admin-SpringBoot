package com.bzq.matrixmall.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bzq.matrixmall.model.entity.product.ProdInfo;
import com.bzq.matrixmall.model.query.product.ProdInfoPageQuery;
import com.bzq.matrixmall.model.vo.product.ProdInfoVO;
import org.apache.ibatis.annotations.Mapper;

//持久层
@Mapper
public interface ProdInfoMapper extends BaseMapper<ProdInfo> {
    //获取商品信息表单详情
    ProdInfo getProdInfoFormData(Long prodId);

    //商品信息分页列表
    IPage<ProdInfoVO> listPageProdInfo(Page<Object> objectPage, ProdInfoPageQuery queryParams);
}
