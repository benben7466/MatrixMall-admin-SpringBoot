package com.bzq.matrixmall.model.vo;

import com.bzq.matrixmall.enums.MessageTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

//访问统计视图对象
@Schema(description ="访问统计视图对象")
@Data
public class VisitStatsVO {
    @Schema(description="类型")
    private String type;

    @Schema(description="标题")
    private String title;

    @Schema(description="今日数量")
    private Integer todayCount;

    @Schema(description="总数")
    private Integer totalCount;

    @Schema(description="增长率")
    private Integer growthRate;

    @Schema(description="粒度标签")
    private Integer granularityLabel;

}
