package com.bzq.matrixmall.controller.product;

import com.bzq.matrixmall.common.result.Result;
import com.bzq.matrixmall.model.form.product.ProdInfoForm;
import com.bzq.matrixmall.plugin.norepeat.annotation.PreventRepeatSubmit;
import com.bzq.matrixmall.service.product.ProdInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//用户控制层
@Tag(name = "101.商品信息接口")
@RestController
@RequestMapping("/api/v1/prod/info")
@RequiredArgsConstructor
public class ProdInfoController {

    private final ProdInfoService prodInfoService;

    @Operation(summary = "新增商品信息")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('prod:info:add')")
    @PreventRepeatSubmit
    public Result<?> saveProdInfo(
            @RequestBody @Valid ProdInfoForm infoForm
    ) {
        boolean result = prodInfoService.saveProdInfo(infoForm);
        return Result.judge(result);
    }

    @Operation(summary = "商品信息表单数据")
    @GetMapping("/{prodId}/form")
    public Result<ProdInfoForm> getProdInfoForm(
            @Parameter(description = "商品ID") @PathVariable Long prodId
    ) {
        ProdInfoForm formData = prodInfoService.getProdInfoFormData(prodId);
        return Result.success(formData);
    }

    @Operation(summary = "修改商品信息")
    @PutMapping(value = "/{prodId}")
    @PreAuthorize("@ss.hasPerm('prod:info:edit')")
    public Result<?> updateProdInfo(
            @Parameter(description = "商品ID") @PathVariable Long prodId,
            @RequestBody @Validated ProdInfoForm prodInfoForm) {
        boolean result = prodInfoService.updateProdInfo(prodId, prodInfoForm);
        return Result.judge(result);
    }

}

