package com.bzq.matrixmall.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

//下拉选项对象
@Schema(description ="键值对")
@Data
@NoArgsConstructor
public class KeyValue {

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValue(Long key, String value) {
        this.keyLong = key;
        this.value = value;
    }

    @Schema(description="选项的值")
    private String key;//默认

    @Schema(description="选项的值")
    private Long keyLong;

    @Schema(description="选项的标签")
    private String value;

}