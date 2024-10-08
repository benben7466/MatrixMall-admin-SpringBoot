package com.bzq.matrixmall.plugin.syslog.annotation;

import java.lang.annotation.*;

//MP数据权限注解

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface DataPermission {
    //数据权限
    String deptAlias() default "";

    String deptIdColumnName() default "dept_id";

    String userAlias() default "";

    String userIdColumnName() default "create_by";
}
