package com.bzq.matrixmall.plugin.syslog.annotation;

import com.bzq.matrixmall.enums.LogModuleEnum;

import java.lang.annotation.*;

//日志注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LogAnnotation {
    String value() default "";

    LogModuleEnum module();
}
