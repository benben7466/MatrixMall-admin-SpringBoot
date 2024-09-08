package com.bzq.matrixmall.converter.system;

import com.bzq.matrixmall.model.entity.system.SysDept;
import com.bzq.matrixmall.model.form.system.DeptForm;
import com.bzq.matrixmall.model.vo.system.DeptVO;
import org.mapstruct.Mapper;

//部门对象转换器
@Mapper(componentModel = "spring")
public interface DeptConverter {
    DeptForm toForm(SysDept entity);

    DeptVO toVo(SysDept entity);

    SysDept toEntity(DeptForm deptForm);
}
