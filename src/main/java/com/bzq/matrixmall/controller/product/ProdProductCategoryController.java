package com.bzq.matrixmall.controller.product;

import com.bzq.matrixmall.common.model.KeyValue;
import com.bzq.matrixmall.common.result.Result;
import com.bzq.matrixmall.service.product.ProdProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//商品与分类控制层
@Tag(name = "103.商品与分类接口")
@RestController
@RequestMapping("/api/v1/product/product_category")
@RequiredArgsConstructor
public class ProdProductCategoryController {

    private final ProdProductCategoryService prodProductCategoryService;


    @Operation(summary = "商品与分类数据")
    @GetMapping("/{prodId}")
    public Result<List<KeyValue>> getProdProductCategory(
            @Parameter(description = "商品ID") @PathVariable Long prodId
    ) {
        List<KeyValue> productCategoryList = prodProductCategoryService.getProdProductCategoryData(prodId);
        return Result.success(productCategoryList);
    }
}

