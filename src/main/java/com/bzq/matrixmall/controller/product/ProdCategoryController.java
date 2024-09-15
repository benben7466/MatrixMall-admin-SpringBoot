package com.bzq.matrixmall.controller.product;

import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.common.result.Result;
import com.bzq.matrixmall.enums.LogModuleEnum;
import com.bzq.matrixmall.model.form.product.ProdCategoryForm;
import com.bzq.matrixmall.model.query.product.ProdCategoryQuery;
import com.bzq.matrixmall.model.vo.product.ProdCategoryVO;
import com.bzq.matrixmall.plugin.norepeat.annotation.PreventRepeatSubmit;
import com.bzq.matrixmall.plugin.syslog.annotation.LogAnnotation;
import com.bzq.matrixmall.service.product.ProdCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//分类控制层
@Tag(name = "102.商品分类接口")
@RestController
@RequestMapping("/api/v1/product/category")
@RequiredArgsConstructor
public class ProdCategoryController {

    private final ProdCategoryService prodCategoryService;

    @Operation(summary = "新增分类")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('prod:category:add')")
    @PreventRepeatSubmit
    public Result<?> saveProdCategory(
            @RequestBody @Valid ProdCategoryForm infoForm
    ) {
        boolean result = prodCategoryService.saveProdCategory(infoForm);
        return Result.judge(result);
    }


    @Operation(summary = "分类表单数据")
    @GetMapping("/{categoryId}/form")
    public Result<ProdCategoryForm> getProdCategoryForm(
            @Parameter(description = "分类ID") @PathVariable Long categoryId
    ) {
        ProdCategoryForm formData = prodCategoryService.getProdCategoryFormData(categoryId);
        return Result.success(formData);
    }

    @Operation(summary = "修改分类")
    @PutMapping(value = "/{categoryId}")
    public Result<?> updateProdCategory(
            @Parameter(description = "分类ID") @PathVariable Long categoryId,
            @RequestBody @Validated ProdCategoryForm formData) {
        Long id = prodCategoryService.updateProdCategory(categoryId, formData);
        return Result.success(id);
    }

    @Operation(summary = "删除分类")
    @DeleteMapping("/{ids}")
    public Result<?> deleteProdCategory(
            @Parameter(description = "分类ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = prodCategoryService.deleteProdCategory(ids);
        return Result.judge(result);
    }

    @Operation(summary = "分类列表")
    @GetMapping("/page")
    @LogAnnotation( value = "分类列表",module = LogModuleEnum.PROD_CATEGORY)
    public Result<List<ProdCategoryVO>> getProdCategoryList(
            ProdCategoryQuery queryParams
    ) {
        List<ProdCategoryVO> list = prodCategoryService.getProdCategoryList(queryParams);
        return Result.success(list);
    }

    @Operation(summary = "分类下拉列表")
    @GetMapping("/options")
    public Result<List<Option<Long>>> getProdCategoryOptions() {
        List<Option<Long>> list = prodCategoryService.listProdCategoryOptions();
        return Result.success(list);
    }

}

