package com.bzq.matrixmall.common.constant;

//JWT Claims声明常量
//JWT Claims 属于 Payload 的一部分，包含了一些实体（通常指的用户）的状态和额外的元数据
public interface JwtClaimConstants {
    String USER_ID = "userId";//用户ID
    String DEPT_ID = "deptId";//部门ID
    String DATA_SCOPE = "dataScope";//数据权限
    String AUTHORITIES = "authorities";//权限(角色Code)集合
}
