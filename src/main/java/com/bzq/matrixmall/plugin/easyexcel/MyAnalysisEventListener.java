package com.bzq.matrixmall.plugin.easyexcel;

import com.alibaba.excel.event.AnalysisEventListener;

//自定义解析结果监听器
public abstract class MyAnalysisEventListener<T> extends AnalysisEventListener<T> {

    private String msg;
    public abstract String getMsg();
}