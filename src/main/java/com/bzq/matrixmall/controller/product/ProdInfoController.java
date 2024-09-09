package com.bzq.matrixmall.controller.product;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bzq.matrixmall.common.result.PageResult;
import com.bzq.matrixmall.common.result.Result;
import com.bzq.matrixmall.enums.LogModuleEnum;
import com.bzq.matrixmall.model.form.product.ProdInfoForm;
import com.bzq.matrixmall.model.query.product.ProdInfoPageQuery;
import com.bzq.matrixmall.model.query.system.UserPageQuery;
import com.bzq.matrixmall.model.vo.product.ProdInfoVO;
import com.bzq.matrixmall.model.vo.system.UserPageVO;
import com.bzq.matrixmall.plugin.norepeat.annotation.PreventRepeatSubmit;
import com.bzq.matrixmall.plugin.syslog.annotation.LogAnnotation;
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

    @Operation(summary = "删除商品信息")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('prod:info:delete')")
    public Result<?> deleteProdInfo(
            @Parameter(description = "商品ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = prodInfoService.deleteProdInfo(ids);
        return Result.judge(result);
    }

    @Operation(summary = "商品信息分页列表")
    @GetMapping("/page")
    @LogAnnotation(value = "商品信息分页列表", module = LogModuleEnum.PROD_INFO)
    public PageResult<ProdInfoVO> listPagedUsers(ProdInfoPageQuery queryParams) {
        IPage<ProdInfoVO> result = prodInfoService.listPageProdInfo(queryParams);
        return PageResult.success(result);
    }

}

