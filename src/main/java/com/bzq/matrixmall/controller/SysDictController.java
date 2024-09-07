package com.bzq.matrixmall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.common.result.PageResult;
import com.bzq.matrixmall.common.result.Result;
import com.bzq.matrixmall.enums.LogModuleEnum;
import com.bzq.matrixmall.model.form.DictForm;
import com.bzq.matrixmall.model.query.DictPageQuery;
import com.bzq.matrixmall.model.vo.DictPageVO;
import com.bzq.matrixmall.plugin.norepeat.annotation.PreventRepeatSubmit;
import com.bzq.matrixmall.plugin.syslog.annotation.LogAnnotation;
import com.bzq.matrixmall.service.SysDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//字典控制层
@Tag(name = "06.字典接口")
@RestController
@RequestMapping("/api/v1/dict")
@RequiredArgsConstructor
public class SysDictController {

    private final SysDictService dictService;

    @Operation(summary = "字典数据项列表")
    @GetMapping("/{code}/options")
    public Result<List<Option<Long>>> getDictOptions(
            @Parameter(description = "字典编码") @PathVariable String code
    ) {
        List<Option<Long>> options = dictService.listDictItemsByCode(code);
        return Result.success(options);
    }

    @Operation(summary = "字典分页列表")
    @GetMapping("/page")
    @LogAnnotation( value = "字典分页列表",module = LogModuleEnum.DICT)
    public PageResult<DictPageVO> getDictPage(
            DictPageQuery queryParams
    ) {
        Page<DictPageVO> result = dictService.getDictPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增字典")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('sys:dict:add')")
    @PreventRepeatSubmit
    public Result<?> saveDict(@RequestBody DictForm formData) {
        boolean result = dictService.saveDict(formData);
        return Result.judge(result);
    }

    @Operation(summary = "字典表单")
    @GetMapping("/{id}/form")
    public Result<DictForm> getDictForm(
            @Parameter(description = "字典ID") @PathVariable Long id
    ) {
        DictForm formData = dictService.getDictForm(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改字典")
    @PutMapping("/{id}")
    @PreAuthorize("@ss.hasPerm('sys:dict:edit')")
    public Result<?> updateDict(
            @PathVariable Long id,
            @RequestBody DictForm DictForm
    ) {
        boolean status = dictService.updateDict(id, DictForm);
        return Result.judge(status);
    }

    @Operation(summary = "删除字典")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('sys:dict:delete')")
    public Result<?> deleteDictionaries(
            @Parameter(description = "字典ID，多个以英文逗号(,)拼接") @PathVariable String ids
    ) {
        dictService.deleteDictByIds(ids);
        return Result.success();
    }

}
