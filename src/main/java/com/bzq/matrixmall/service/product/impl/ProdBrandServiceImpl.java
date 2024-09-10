package com.bzq.matrixmall.service.product.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.common.constant.SystemConstants;
import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.converter.product.ProdBrandConverter;
import com.bzq.matrixmall.mapper.product.ProdBrandMapper;
import com.bzq.matrixmall.model.entity.product.ProdBrand;
import com.bzq.matrixmall.model.entity.system.SysRole;
import com.bzq.matrixmall.model.form.product.ProdBrandForm;
import com.bzq.matrixmall.model.query.product.ProdBrandPageQuery;
import com.bzq.matrixmall.model.vo.product.ProdBrandPageVO;
import com.bzq.matrixmall.security.util.SecurityUtils;
import com.bzq.matrixmall.service.product.ProdBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdBrandServiceImpl extends ServiceImpl<ProdBrandMapper, ProdBrand> implements ProdBrandService {

    private final ProdBrandConverter prodBrandConverter;

    //新增商品品牌
    @Override
    public boolean saveProdBrand(ProdBrandForm formData) {
        String brandName = formData.getBrandName();

        long count = this.count(new LambdaQueryWrapper<ProdBrand>().eq(ProdBrand::getBrandName, brandName));
        Assert.isTrue(count == 0, "品牌名称已存在");

        // 实体转换 form->entity
        ProdBrand entity = prodBrandConverter.toEntity(formData);

        boolean result = this.save(entity);
        Assert.isTrue(result, "品牌保存失败");

        return result;
    }

    //获取商品品牌表单数据
    @Override
    public ProdBrandForm getProdBrandFormData(Long brandId) {
        ProdBrand entity = this.getById(brandId);
        return prodBrandConverter.toForm(entity);
    }

    //更新商品品牌
    @Override
    public boolean updateProdBrand(Long brandId, ProdBrandForm formData) {
        String brandName = formData.getBrandName();

        long count = this.count(new LambdaQueryWrapper<ProdBrand>()
                .eq(ProdBrand::getBrandName, brandName)
                .ne(ProdBrand::getId, brandId)
        );
        Assert.isTrue(count == 0, "品牌名称已存在");

        // form -> entity
        ProdBrand entity = prodBrandConverter.toEntity(formData);
        entity.setId(brandId);

        return this.updateById(entity);
    }

    //删除商品品牌
    @Override
    public boolean deleteProdBrand(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除的品牌数据为空");

        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    //商品品牌分页列表
    @Override
    public IPage<ProdBrandPageVO> listPageProdBrand(ProdBrandPageQuery queryParams) {
        // 查询参数
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        String keywords = queryParams.getKeywords();

        // 查询数据
        Page<ProdBrand> brandPage = this.page(new Page<>(pageNum, pageSize), new LambdaQueryWrapper<ProdBrand>()
                .and(StrUtil.isNotBlank(keywords), wrapper -> wrapper.like(StrUtil.isNotBlank(keywords), ProdBrand::getBrandName, keywords))
        );

        // 实体转换
        return prodBrandConverter.toPageVo(brandPage);
    }

    //品牌下拉列表
    @Override
    public List<Option<Long>> listBrandOptions() {
        // 查询数据
        List<ProdBrand> brandList = this.list(new LambdaQueryWrapper<ProdBrand>()
                .select(ProdBrand::getId, ProdBrand::getBrandName)
                .eq(ProdBrand::getStatus, 1)
                .orderByAsc(ProdBrand::getSort));

        // 实体转换
        return prodBrandConverter.entities2Options(brandList);
    }
}
