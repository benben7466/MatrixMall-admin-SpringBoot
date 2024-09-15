package com.bzq.matrixmall.service.product.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.common.model.KeyValue;
import com.bzq.matrixmall.common.model.KeyValueLong;
import com.bzq.matrixmall.converter.product.ProdProductCategoryConverter;
import com.bzq.matrixmall.mapper.product.ProdProductCategoryMapper;
import com.bzq.matrixmall.model.entity.product.ProdProductCategory;
import com.bzq.matrixmall.service.product.ProdProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdProductCategoryServiceImpl extends ServiceImpl<ProdProductCategoryMapper, ProdProductCategory> implements ProdProductCategoryService {
    private final ProdProductCategoryConverter prodProductCategoryConverter;

    @Override
    public void saveProductCategory(Long productId, List<Long> categoryIds) {
        if (productId == null || CollectionUtil.isEmpty(categoryIds)) {
            return;
        }

        // 原ID集合
        List<Long> oldCategoryIds = this.list(new LambdaQueryWrapper<ProdProductCategory>()
                        .eq(ProdProductCategory::getProductId, productId))
                .stream()
                .map(ProdProductCategory::getCategoryId)
                .collect(Collectors.toList());

        // 新增
        List<Long> saveCategoryIds;
        if (CollectionUtil.isEmpty(oldCategoryIds)) {
            saveCategoryIds = categoryIds;
        } else {
            saveCategoryIds = categoryIds.stream()
                    .filter(roleId -> !oldCategoryIds.contains(roleId))
                    .collect(Collectors.toList());
        }

        List<ProdProductCategory> saveProductCategory = saveCategoryIds
                .stream()
                .map(categoryId -> new ProdProductCategory(productId, categoryId))
                .collect(Collectors.toList());
        this.saveBatch(saveProductCategory);

        // 删除
        if (CollectionUtil.isNotEmpty(oldCategoryIds)) {
            List<Long> removeCategoryIds = oldCategoryIds.stream()
                    .filter(categoryId -> !categoryIds.contains(categoryId))
                    .collect(Collectors.toList());

            if (CollectionUtil.isNotEmpty(removeCategoryIds)) {
                this.remove(new LambdaQueryWrapper<ProdProductCategory>()
                        .eq(ProdProductCategory::getProductId, productId)
                        .in(ProdProductCategory::getCategoryId, removeCategoryIds)
                );
            }
        }
    }

    @Override
    public void deleteProductCategory(Long productId) {
        this.remove(new LambdaQueryWrapper<ProdProductCategory>()
                .eq(ProdProductCategory::getProductId, productId)
        );
    }

    //商品与分类数据
    @Override
    public List<KeyValue> getProdProductCategoryData(Long prodId) {
        // 查询数据
        List<ProdProductCategory> prodCategoryList = this.list(
                new LambdaQueryWrapper<ProdProductCategory>()
                        .eq(ProdProductCategory::getProductId, prodId)
                        .orderByAsc(ProdProductCategory::getId)
        );

        List<KeyValue> transformedList = prodCategoryList
                .stream().map(entry -> new KeyValue(entry.getCategoryId(), ""))
                .toList();

        return transformedList;
    }
}
