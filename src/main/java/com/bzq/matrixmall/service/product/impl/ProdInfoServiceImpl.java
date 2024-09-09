package com.bzq.matrixmall.service.product.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.converter.product.ProdInfoConverter;
import com.bzq.matrixmall.mapper.product.ProdInfoMapper;
import com.bzq.matrixmall.model.entity.product.ProdInfo;
import com.bzq.matrixmall.model.entity.system.SysUser;
import com.bzq.matrixmall.model.form.product.ProdInfoForm;
import com.bzq.matrixmall.service.product.ProdInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdInfoServiceImpl extends ServiceImpl<ProdInfoMapper, ProdInfo> implements ProdInfoService {

    private final ProdInfoConverter prodInfoConverter;

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
}
