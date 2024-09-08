package com.bzq.matrixmall.plugin.easyexcel;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bzq.matrixmall.common.base.IBaseEnum;
import com.bzq.matrixmall.common.constant.SystemConstants;
import com.bzq.matrixmall.converter.system.UserConverter;
import com.bzq.matrixmall.enums.GenderEnum;
import com.bzq.matrixmall.enums.StatusEnum;
import com.bzq.matrixmall.model.dto.system.UserImportDTO;
import com.bzq.matrixmall.model.entity.system.SysDept;
import com.bzq.matrixmall.model.entity.system.SysRole;
import com.bzq.matrixmall.model.entity.system.SysUser;
import com.bzq.matrixmall.model.entity.system.SysUserRole;
import com.bzq.matrixmall.service.system.SysDeptService;
import com.bzq.matrixmall.service.system.SysRoleService;
import com.bzq.matrixmall.service.system.SysUserRoleService;
import com.bzq.matrixmall.service.system.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class UserImportListener extends MyAnalysisEventListener<UserImportDTO> {
    private int validCount;// 有效条数
    private int invalidCount;// 无效条数
    StringBuilder msg = new StringBuilder();// 导入返回信息

    private final SysUserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;
    private final SysRoleService roleService;
    private final SysUserRoleService userRoleService;
    private final SysDeptService deptService;

    public UserImportListener() {
        this.userService = SpringUtil.getBean(SysUserService.class);
        this.passwordEncoder = SpringUtil.getBean(PasswordEncoder.class);
        this.roleService = SpringUtil.getBean(SysRoleService.class);
        this.userRoleService = SpringUtil.getBean(SysUserRoleService.class);
        this.deptService = SpringUtil.getBean(SysDeptService.class);
        this.userConverter = SpringUtil.getBean(UserConverter.class);
    }


    //每一条数据解析都会来调用
    @Override
    public void invoke(UserImportDTO userImportDTO, AnalysisContext analysisContext) {
        log.info("解析到一条用户数据:{}", JSONUtil.toJsonStr(userImportDTO));

        // 校验数据
        StringBuilder validationMsg = new StringBuilder();

        String username = userImportDTO.getUsername();
        if (StrUtil.isBlank(username)) {
            validationMsg.append("用户名为空；");
        } else {
            long count = userService.count(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
            if (count > 0) {
                validationMsg.append("用户名已存在；");
            }
        }

        String nickname = userImportDTO.getNickname();
        if (StrUtil.isBlank(nickname)) {
            validationMsg.append("用户昵称为空；");
        }

        String mobile = userImportDTO.getMobile();
        if (StrUtil.isBlank(mobile)) {
            validationMsg.append("手机号码为空；");
        } else {
            if (!Validator.isMobile(mobile)) {
                validationMsg.append("手机号码不正确；");
            }
        }

        if (validationMsg.isEmpty()) {
            // 校验通过，持久化至数据库
            SysUser entity = userConverter.toEntity(userImportDTO);
            entity.setPassword(passwordEncoder.encode(SystemConstants.DEFAULT_PASSWORD));   // 默认密码
            // 性别翻译
            String genderLabel = userImportDTO.getGenderLabel();
            if (StrUtil.isNotBlank(genderLabel)) {
                Integer genderValue = (Integer) IBaseEnum.getValueByLabel(genderLabel, GenderEnum.class);
                entity.setGender(genderValue);
            }

            // 角色解析
            String roleCodes = userImportDTO.getRoleCodes();
            List<Long> roleIds = null;
            if (StrUtil.isNotBlank(roleCodes)) {
                roleIds = roleService.list(
                                new LambdaQueryWrapper<SysRole>()
                                        .in(SysRole::getCode, (Object) roleCodes.split(","))
                                        .eq(SysRole::getStatus, StatusEnum.ENABLE.getValue())
                                        .select(SysRole::getId)
                        ).stream()
                        .map(SysRole::getId)
                        .collect(Collectors.toList());
            }

            // 部门解析
            String deptCode = userImportDTO.getDeptCode();
            if (StrUtil.isNotBlank(deptCode)) {
                SysDept dept = deptService.getOne(new LambdaQueryWrapper<SysDept>().eq(SysDept::getCode, deptCode)
                        .select(SysDept::getId)
                );
                if (dept != null) {
                    entity.setDeptId(dept.getId());
                }
            }


            boolean saveResult = userService.save(entity);
            if (saveResult) {
                validCount++;
                // 保存用户角色关联
                if (CollectionUtil.isNotEmpty(roleIds)) {
                    List<SysUserRole> userRoles = roleIds.stream()
                            .map(roleId -> new SysUserRole(entity.getId(), roleId))
                            .collect(Collectors.toList());
                    userRoleService.saveBatch(userRoles);
                }
            } else {
                invalidCount++;
                msg.append("第").append(validCount + invalidCount).append("行数据保存失败；<br/>");
            }
        } else {
            invalidCount++;
            msg.append("第").append(validCount + invalidCount).append("行数据校验失败：").append(validationMsg + "<br/>");
        }

    }

    //所有数据解析完成会来调用
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
    }

    @Override
    public String getMsg() {
        return StrUtil.format("导入用户结束：成功{}条，失败{}条；<br/>{}", validCount, invalidCount, msg);
    }
}
