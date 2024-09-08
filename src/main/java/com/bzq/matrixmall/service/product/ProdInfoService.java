package com.bzq.matrixmall.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.model.entity.product.ProdInfo;
import com.bzq.matrixmall.model.form.product.ProdInfoForm;

public interface ProdInfoService extends IService<ProdInfo> {

    //新增商品信息
    boolean saveProdInfo(ProdInfoForm infoForm);

    //获取商品信息表单数据
    ProdInfoForm getProdInfoFormData(Long prodId);

    //更新商品信息
    boolean updateProdInfo(Long prodId, ProdInfoForm prodInfoForm);

}
