package com.bzq.matrixmall.controller.product;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.common.result.PageResult;
import com.bzq.matrixmall.common.result.Result;
import com.bzq.matrixmall.enums.LogModuleEnum;
import com.bzq.matrixmall.model.form.product.ProdBrandForm;
import com.bzq.matrixmall.model.query.product.ProdBrandPageQuery;
import com.bzq.matrixmall.model.vo.product.ProdBrandPageVO;
import com.bzq.matrixmall.plugin.norepeat.annotation.PreventRepeatSubmit;
import com.bzq.matrixmall.plugin.syslog.annotation.LogAnnotation;
import com.bzq.matrixmall.service.product.ProdBrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//品牌控制层
@Tag(name = "103.商品品牌接口")
@RestController
@RequestMapping("/api/v1/product/brand")
@RequiredArgsConstructor
public class ProdBrandController {

    private final ProdBrandService prodBrandService;

    @Operation(summary = "新增品牌")
    @PostMapping
    @PreventRepeatSubmit
    public Result<?> saveProdBrand(
            @RequestBody @Valid ProdBrandForm infoForm
    ) {
        boolean result = prodBrandService.saveProdBrand(infoForm);
        return Result.judge(result);
    }

    @Operation(summary = "商品品牌表单数据")
    @GetMapping("/{brandId}/form")
    public Result<ProdBrandForm> getProdBrandForm(
            @Parameter(description = "品牌ID") @PathVariable Long brandId
    ) {
        ProdBrandForm formData = prodBrandService.getProdBrandFormData(brandId);
        return Result.success(formData);
    }

    @Operation(summary = "修改商品品牌")
    @PutMapping(value = "/{brandId}")
    public Result<?> updateProdBrand(
            @Parameter(description = "品牌ID") @PathVariable Long brandId,
            @RequestBody @Validated ProdBrandForm formData) {
        boolean result = prodBrandService.updateProdBrand(brandId, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除商品品牌")
    @DeleteMapping("/{ids}")
    public Result<?> deleteProdBrand(
            @Parameter(description = "品牌ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = prodBrandService.deleteProdBrand(ids);
        return Result.judge(result);
    }

    @Operation(summary = "商品品牌分页列表")
    @GetMapping("/page")
    @LogAnnotation(value = "商品品牌分页列表", module = LogModuleEnum.PROD_BRAND)
    public PageResult<ProdBrandPageVO> listPagedBrand(ProdBrandPageQuery queryParams) {
        IPage<ProdBrandPageVO> result = prodBrandService.listPageProdBrand(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "品牌下拉列表")
    @GetMapping("/options")
    public Result<List<Option<Long>>> listRoleOptions() {
        List<Option<Long>> list = prodBrandService.listBrandOptions();
        return Result.success(list);
    }

}

