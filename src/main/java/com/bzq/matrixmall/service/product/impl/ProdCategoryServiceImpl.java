package com.bzq.matrixmall.service.product.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.common.constant.SymbolConstant;
import com.bzq.matrixmall.common.constant.SystemConstants;
import com.bzq.matrixmall.converter.product.ProdCategoryConverter;
import com.bzq.matrixmall.mapper.product.ProdCategoryMapper;
import com.bzq.matrixmall.model.entity.product.ProdBrand;
import com.bzq.matrixmall.model.entity.product.ProdCategory;
import com.bzq.matrixmall.model.entity.system.SysDept;
import com.bzq.matrixmall.model.form.product.ProdBrandForm;
import com.bzq.matrixmall.model.form.product.ProdCategoryForm;
import com.bzq.matrixmall.model.query.product.ProdCategoryQuery;
import com.bzq.matrixmall.model.vo.product.ProdCategoryVO;
import com.bzq.matrixmall.model.vo.system.DeptVO;
import com.bzq.matrixmall.service.product.ProdCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdCategoryServiceImpl extends ServiceImpl<ProdCategoryMapper, ProdCategory> implements ProdCategoryService {

    private final ProdCategoryConverter prodCategoryConverter;

    //新增分类
    @Override
    public boolean saveProdCategory(ProdCategoryForm formData) {
        String categoryName = formData.getCategoryName();

        // 校验分类名称是否存在
        long count = this.count(new LambdaQueryWrapper<ProdCategory>()
                .eq(ProdCategory::getCategoryName, categoryName)
        );
        Assert.isTrue(count == 0, "分类名称已存在");

        // 实体转换 form->entity
        ProdCategory entity = prodCategoryConverter.toEntity(formData);

        // 生成路径(tree_path)，格式：父节点tree_path + , + 父节点ID，用于删除时删除子分类
        String treePath = generateDeptTreePath(formData.getParentId());
        entity.setTreePath(treePath);

        boolean result = this.save(entity);
        Assert.isTrue(result, "分类保存失败");

        return result;
    }

    //分类表单数据
    @Override
    public ProdCategoryForm getProdCategoryFormData(Long categoryId) {
        ProdCategory entity = this.getById(categoryId);
        return prodCategoryConverter.toForm(entity);
    }

    //修改分类
    @Override
    public Long updateProdCategory(Long categoryId, ProdCategoryForm formData) {
        // 校验分类是否存在
        String categoryName = formData.getCategoryName();
        long count = this.count(new LambdaQueryWrapper<ProdCategory>()
                .ne(ProdCategory::getId, categoryId)
                .eq(ProdCategory::getCategoryName, categoryName)
        );
        Assert.isTrue(count == 0, "分类名称已存在");

        // form->entity
        ProdCategory entity = prodCategoryConverter.toEntity(formData);
        entity.setId(categoryId);

        // 生成部门路径(tree_path)，格式：父节点tree_path + , + 父节点ID，用于删除部门时级联删除子部门
        String treePath = generateDeptTreePath(formData.getParentId());
        entity.setTreePath(treePath);

        // 保存部门并返回部门ID
        boolean result = this.updateById(entity);
        Assert.isTrue(result, "部门更新失败");

        return entity.getId();
    }

    //删除分类
    @Override
    public boolean deleteProdCategory(String ids) {
        // 删除分类及子部门
        if (StrUtil.isNotBlank(ids)) {
            String[] categoryIds = ids.split(SymbolConstant.COMMA);
            for (String categoryId : categoryIds) {
                this.remove(new LambdaQueryWrapper<ProdCategory>()
                        .eq(ProdCategory::getId, categoryId)
                        .or()
                        .apply("CONCAT (',',tree_path,',') LIKE CONCAT('%,',{0},',%')", categoryId));
            }
        }
        return true;
    }

    //分类列表
    @Override
    public List<ProdCategoryVO> getProdCategoryList(ProdCategoryQuery queryParams) {
        // 查询参数
        String keywords = queryParams.getKeywords();

        // 查询数据
        List<ProdCategory> prodCategoryList = this.list(
                new LambdaQueryWrapper<ProdCategory>()
                        .like(StrUtil.isNotBlank(keywords), ProdCategory::getCategoryName, keywords)
                        .orderByAsc(ProdCategory::getSort)
        );

        if (CollectionUtil.isEmpty(prodCategoryList)) {
            return Collections.EMPTY_LIST;
        }

        // 获取所有分类ID
        Set<Long> prodCategoryIds = prodCategoryList.stream()
                .map(ProdCategory::getId)
                .collect(Collectors.toSet());

        // 获取父节点ID
        Set<Long> parentIds = prodCategoryList.stream()
                .map(ProdCategory::getParentId)
                .collect(Collectors.toSet());

        // 获取根节点ID（递归的起点），即父节点ID中不包含在ID中的节点，注意这里不能拿顶级 O 作为根节点，因为筛选的时候 O 会被过滤掉
        List<Long> rootIds = CollectionUtil.subtractToList(parentIds, prodCategoryIds);

        // 递归生成部门树形列表
        return rootIds.stream()
                .flatMap(rootId -> recurCategoryList(rootId, prodCategoryList).stream())
                .toList();
    }

    //递归生成树形列表
    public List<ProdCategoryVO> recurCategoryList(Long parentId, List<ProdCategory> prodCategoryList) {
        return prodCategoryList.stream()
                .filter(cate -> cate.getParentId().equals(parentId))
                .map(cate -> {
                    ProdCategoryVO prodCategoryVO = prodCategoryConverter.toVo(cate);
                    List<ProdCategoryVO> children = recurCategoryList(cate.getId(), prodCategoryList);
                    prodCategoryVO.setChildren(children);
                    return prodCategoryVO;
                }).toList();
    }

    //路径生成
    private String generateDeptTreePath(Long parentId) {
        String treePath = null;
        if (SystemConstants.ROOT_NODE_ID.equals(parentId)) {
            treePath = String.valueOf(parentId);
        } else {
            ProdCategory parent = this.getById(parentId);
            if (parent != null) {
                treePath = parent.getTreePath() + SymbolConstant.COMMA + parent.getId();
            }
        }
        return treePath;
    }


}
