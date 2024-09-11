package com.bzq.matrixmall.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.model.entity.product.ProdCategory;
import com.bzq.matrixmall.model.form.product.ProdCategoryForm;

public interface ProdCategoryService extends IService<ProdCategory> {
    //新增分类
    boolean saveProdCategory(ProdCategoryForm infoForm);

    //分类表单数据
    ProdCategoryForm getProdCategoryFormData(Long categoryId);

    //修改分类
    Long updateProdCategory(Long categoryId, ProdCategoryForm formData);

    //删除分类
    boolean deleteProdCategory(String ids);
}
