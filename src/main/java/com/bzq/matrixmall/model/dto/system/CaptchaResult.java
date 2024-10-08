package com.bzq.matrixmall.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

//验证码数据传输对象
@Schema(description = "验证码响应对象")
@Builder
@Data
public class CaptchaResult {
    @Schema(description = "验证码ID")
    private String captchaKey;

    @Schema(description = "验证码图片Base64字符串")
    private String captchaBase64;
}
