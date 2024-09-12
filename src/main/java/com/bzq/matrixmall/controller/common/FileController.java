package com.bzq.matrixmall.controller.common;

import com.bzq.matrixmall.common.result.Result;
import com.bzq.matrixmall.model.dto.common.FileInfo;
import com.bzq.matrixmall.service.common.OssService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//文件控制层
@Tag(name = "07.文件接口")
@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final OssService ossService;

    @Operation(summary = "文件上传")
    @PostMapping
    public Result<FileInfo> uploadFile(
            @Parameter(name = "file", description = "表单文件对象", required = true, in = ParameterIn.DEFAULT, schema = @Schema(name = "file", format = "binary"))
            @RequestPart(value = "file") MultipartFile file
    ) {
        FileInfo fileInfo = ossService.uploadFile(file);
        return Result.success(fileInfo);
    }

    @Operation(summary = "文件删除")
    @DeleteMapping
    @SneakyThrows
    public Result<?> deleteFile(
            @Parameter(description = "文件路径") @RequestParam String filePath
    ) {
        boolean result = ossService.deleteFile(filePath);
        return Result.judge(result);
    }
}
