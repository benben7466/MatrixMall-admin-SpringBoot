package com.bzq.matrixmall.plugin.norepeat.annotation;

import java.lang.annotation.*;

//防止重复提交注解
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PreventRepeatSubmit {

    //锁过期时间（秒），默认5秒内不允许重复提交
    int expire() default 6;
}
