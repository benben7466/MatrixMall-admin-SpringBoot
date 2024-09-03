package com.bzq.matrixmall.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.bzq.matrixmall.enums.MessageTypeEnum;

//消息视图对象
@Schema(description ="消息视图对象")
@Data
public class MessageVO {
    @Schema(description="消息ID")
    private Long messageId;

    @Schema(description="标题")
    private String title;

    @Schema(description="类型")
    private MessageTypeEnum type;
}
