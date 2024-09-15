package com.bzq.matrixmall.service.product.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.common.model.KeyValue;
import com.bzq.matrixmall.converter.product.ProdInfoConverter;
import com.bzq.matrixmall.mapper.product.ProdInfoMapper;
import com.bzq.matrixmall.model.bo.system.UserBO;
import com.bzq.matrixmall.model.entity.product.ProdInfo;
import com.bzq.matrixmall.model.entity.product.ProdProductCategory;
import com.bzq.matrixmall.model.entity.system.SysUser;
import com.bzq.matrixmall.model.form.product.ProdInfoForm;
import com.bzq.matrixmall.model.query.product.ProdInfoPageQuery;
import com.bzq.matrixmall.model.vo.product.ProdInfoVO;
import com.bzq.matrixmall.service.product.ProdInfoService;
import com.bzq.matrixmall.service.product.ProdProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdInfoServiceImpl extends ServiceImpl<ProdInfoMapper, ProdInfo> implements ProdInfoService {

    private final ProdInfoConverter prodInfoConverter;
    private final ProdProductCategoryService prodProductCategoryService;

    //新增商品信息
    @Override
    public boolean saveProdInfo(ProdInfoForm prodInfoForm) {
        String prodName = prodInfoForm.getProductName();

        long count = this.count(new LambdaQueryWrapper<ProdInfo>().eq(ProdInfo::getProductName, prodName));
        Assert.isTrue(count == 0, "商品名称已存在");

        // 实体转换 form->entity
        ProdInfo entity = prodInfoConverter.toEntity(prodInfoForm);

        boolean result = this.save(entity);
        Assert.isTrue(result, "商品信息保存失败");

        return result;
    }

    //获取商品信息表单数据
    @Override
    public ProdInfoForm getProdInfoFormData(Long prodId) {
        ProdInfo prodInfo = this.baseMapper.getProdInfoFormData(prodId);

        // 实体转换 form->entity
        return prodInfoConverter.toForm(prodInfo);
    }

    //更新商品信息
    @Override
    public boolean updateProdInfo(Long prodId, ProdInfoForm prodInfoForm) {
        String prodName = prodInfoForm.getProductName();

        long count = this.count(new LambdaQueryWrapper<ProdInfo>()
                .eq(ProdInfo::getProductName, prodName)
                .ne(ProdInfo::getId, prodId)
        );
        Assert.isTrue(count == 0, "商品名称已存在");

        // form -> entity
        ProdInfo entity = prodInfoConverter.toEntity(prodInfoForm);
        entity.setId(prodId);

        //保存分类
        List<KeyValue> categoryIds = prodInfoForm.getCategoryIds();
        if (CollectionUtil.isNotEmpty(categoryIds)) {
            List<Long> saveCategoryIds = categoryIds
                    .stream()
                    .filter(item -> StrUtil.isNotBlank(item.getKey()))
                    .map(item -> Convert.toLong(item.getKey()))
                    .collect(Collectors.toList());

            prodProductCategoryService.saveProductCategory(prodId, saveCategoryIds);
        } else {
            //清空
            prodProductCategoryService.deleteProductCategory(prodId);
        }

        return this.updateById(entity);
    }

    //删除商品信息
    @Override
    public boolean deleteProdInfo(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除的商品数据为空");

        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    //商品信息分页列表
    @Override
    public IPage<ProdInfoVO> listPageProdInfo(ProdInfoPageQuery queryParams) {
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();

        // 查询数据
        return this.baseMapper.listPageProdInfo(new Page<>(pageNum, pageSize), queryParams);
    }
}
