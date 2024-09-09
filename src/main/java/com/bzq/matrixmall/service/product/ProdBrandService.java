package com.bzq.matrixmall.service.product;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.model.entity.product.ProdBrand;
import com.bzq.matrixmall.model.form.product.ProdBrandForm;
import com.bzq.matrixmall.model.query.product.ProdBrandPageQuery;
import com.bzq.matrixmall.model.vo.product.ProdBrandPageVO;

public interface ProdBrandService extends IService<ProdBrand> {

    //新增商品品牌
    boolean saveProdBrand(ProdBrandForm infoForm);

    //获取商品品牌表单数据
    ProdBrandForm getProdBrandFormData(Long prodId);

    //更新商品品牌
    boolean updateProdBrand(Long prodId, ProdBrandForm formData);

    //删除商品品牌
    boolean deleteProdBrand(String ids);

    //商品品牌分页列表
    IPage<ProdBrandPageVO> listPageProdBrand(ProdBrandPageQuery queryParams);
}
