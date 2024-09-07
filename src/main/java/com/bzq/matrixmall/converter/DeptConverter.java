package com.bzq.matrixmall.converter;

import com.bzq.matrixmall.model.entity.SysDept;
import com.bzq.matrixmall.model.form.DeptForm;
import com.bzq.matrixmall.model.vo.DeptVO;
import org.mapstruct.Mapper;

//部门对象转换器
@Mapper(componentModel = "spring")
public interface DeptConverter {
    DeptForm toForm(SysDept entity);

    DeptVO toVo(SysDept entity);

    SysDept toEntity(DeptForm deptForm);
}
