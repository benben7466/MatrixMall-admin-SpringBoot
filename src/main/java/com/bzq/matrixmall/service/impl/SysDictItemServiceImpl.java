package com.bzq.matrixmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.mapper.SysDictItemMapper;
import com.bzq.matrixmall.model.entity.SysDictItem;
import com.bzq.matrixmall.service.SysDictItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//数据字典 服务实现类
@Service
@RequiredArgsConstructor
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {

}

