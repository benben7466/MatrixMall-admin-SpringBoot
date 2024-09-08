package com.bzq.matrixmall.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.model.entity.system.SysDict;
import com.bzq.matrixmall.model.form.system.DictForm;
import com.bzq.matrixmall.model.query.system.DictPageQuery;
import com.bzq.matrixmall.model.vo.system.DictPageVO;

import java.util.List;

//数据字典业务接口
public interface SysDictService extends IService<SysDict> {

    //获取字典的数据项
    List<Option<Long>> listDictItemsByCode(String code);

    //字典分页列表
    Page<DictPageVO> getDictPage(DictPageQuery queryParams);

    //新增字典
    boolean saveDict(DictForm dictForm);

    //删除字典
    void deleteDictByIds(String ids);

    //获取字典表单详情
    DictForm getDictForm(Long id);

    //修改字典
    boolean updateDict(Long id, DictForm dictForm);
}
