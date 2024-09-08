package com.bzq.matrixmall.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.model.entity.system.SysDept;
import com.bzq.matrixmall.model.form.system.DeptForm;
import com.bzq.matrixmall.model.query.system.DeptQuery;
import com.bzq.matrixmall.model.vo.system.DeptVO;

import java.util.List;

//部门业务接口
public interface SysDeptService extends IService<SysDept> {
    //部门树形下拉选项
    List<Option<Long>> listDeptOptions();

    //部门列表
    List<DeptVO> getDeptList(DeptQuery queryParams);

    //新增部门
    Long saveDept(DeptForm formData);

    //删除部门
    boolean deleteByIds(String ids);

    //获取部门详情
    DeptForm getDeptForm(Long deptId);

    //修改部门
    Long updateDept(Long deptId, DeptForm formData);
}
