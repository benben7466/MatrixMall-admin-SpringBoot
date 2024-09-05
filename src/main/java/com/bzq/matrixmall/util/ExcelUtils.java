package com.bzq.matrixmall.util;

import com.alibaba.excel.EasyExcel;
import com.bzq.matrixmall.plugin.easyexcel.MyAnalysisEventListener;

import java.io.InputStream;

//Excel 工具类
public class ExcelUtils {
    public static <T> String importExcel(InputStream is, Class clazz, MyAnalysisEventListener<T> listener) {
        EasyExcel.read(is, clazz, listener).sheet().doRead();
        return listener.getMsg();
    }
}
