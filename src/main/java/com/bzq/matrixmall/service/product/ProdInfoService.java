package com.bzq.matrixmall.service.product;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.model.entity.product.ProdInfo;
import com.bzq.matrixmall.model.form.product.ProdInfoForm;
import com.bzq.matrixmall.model.query.product.ProdInfoPageQuery;
import com.bzq.matrixmall.model.vo.product.ProdInfoVO;

public interface ProdInfoService extends IService<ProdInfo> {

    //新增商品信息
    boolean saveProdInfo(ProdInfoForm infoForm);

    //获取商品信息表单数据
    ProdInfoForm getProdInfoFormData(Long prodId);

    //更新商品信息
    boolean updateProdInfo(Long prodId, ProdInfoForm prodInfoForm);

    //删除商品信息
    boolean deleteProdInfo(String ids);

    //商品信息分页列表
    IPage<ProdInfoVO> listPageProdInfo(ProdInfoPageQuery queryParams);
}
