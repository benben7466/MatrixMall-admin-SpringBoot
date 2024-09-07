package com.bzq.matrixmall.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzq.matrixmall.common.constant.SystemConstants;
import com.bzq.matrixmall.common.model.Option;
import com.bzq.matrixmall.converter.RoleConverter;
import com.bzq.matrixmall.mapper.SysRoleMapper;
import com.bzq.matrixmall.model.entity.SysRole;
import com.bzq.matrixmall.model.entity.SysRoleMenu;
import com.bzq.matrixmall.model.form.RoleForm;
import com.bzq.matrixmall.model.query.RolePageQuery;
import com.bzq.matrixmall.model.vo.RolePageVO;
import com.bzq.matrixmall.security.util.SecurityUtils;
import com.bzq.matrixmall.service.SysRoleMenuService;
import com.bzq.matrixmall.service.SysRoleService;
import com.bzq.matrixmall.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

//角色业务实现类

@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final RoleConverter roleConverter;
    private final SysRoleMenuService roleMenuService;
    private final SysUserRoleService userRoleService;

    //获取最大范围的数据权限
    @Override
    public Integer getMaximumDataScope(Set<String> roles) {
        Integer dataScope = this.baseMapper.getMaximumDataScope(roles);
        return dataScope;
    }

    //角色下拉列表
    @Override
    public List<Option<Long>> listRoleOptions() {
        // 查询数据
        List<SysRole> roleList = this.list(new LambdaQueryWrapper<SysRole>().ne(!SecurityUtils.isRoot(), SysRole::getCode, SystemConstants.ROOT_ROLE_CODE).select(SysRole::getId, SysRole::getName).orderByAsc(SysRole::getSort));

        // 实体转换
        return roleConverter.entities2Options(roleList);
    }

    //角色分页列表
    @Override
    public Page<RolePageVO> getRolePage(RolePageQuery queryParams) {
        // 查询参数
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        String keywords = queryParams.getKeywords();

        // 查询数据
        Page<SysRole> rolePage = this.page(new Page<>(pageNum, pageSize), new LambdaQueryWrapper<SysRole>().and(StrUtil.isNotBlank(keywords), wrapper -> wrapper.like(StrUtil.isNotBlank(keywords), SysRole::getName, keywords).or().like(StrUtil.isNotBlank(keywords), SysRole::getCode, keywords)).ne(!SecurityUtils.isRoot(), SysRole::getCode, SystemConstants.ROOT_ROLE_CODE) // 非超级管理员不显示超级管理员角色
        );

        // 实体转换
        return roleConverter.toPageVo(rolePage);
    }

    //保存角色
    @Override
    public boolean saveRole(RoleForm roleForm) {
        Long roleId = roleForm.getId();

        // 编辑角色时，判断角色是否存在
        SysRole oldRole = null;
        if (roleId != null) {
            oldRole = this.getById(roleId);
            Assert.isTrue(oldRole != null, "角色不存在");//确保在编辑角色时，要编辑的角色确实存在于系统中
        }

        String roleCode = roleForm.getCode();
        long count = this.count(new LambdaQueryWrapper<SysRole>().ne(roleId != null, SysRole::getId, roleId)//把自己排除
                .and(wrapper -> wrapper.eq(SysRole::getCode, roleCode).or().eq(SysRole::getName, roleForm.getName())));
        Assert.isTrue(count == 0, "角色名称或角色编码已存在，请修改后重试！");

        // 实体转换
        SysRole role = roleConverter.toEntity(roleForm);

        boolean result = this.saveOrUpdate(role);
        if (result) {
            // 判断角色编码或状态是否修改，修改了则刷新权限缓存
            if (oldRole != null && (!StrUtil.equals(oldRole.getCode(), roleCode) || !ObjectUtil.equals(oldRole.getStatus(), roleForm.getStatus()))) {
                roleMenuService.refreshRolePermsCache(oldRole.getCode(), roleCode);
            }
        }

        return result;
    }

    //批量删除角色
    @Override
    public boolean deleteRoles(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的角色ID不能为空");
        List<Long> roleIds = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();

        for (Long roleId : roleIds) {
            SysRole role = this.getById(roleId);
            Assert.isTrue(role != null, "角色不存在");

            // 判断角色是否被用户关联
            boolean isRoleAssigned = userRoleService.hasAssignedUsers(roleId);
            Assert.isTrue(!isRoleAssigned, "角色【{}】已分配用户，请先解除关联后删除", role.getName());

            boolean deleteResult = this.removeById(roleId);
            if (deleteResult) {
                // 删除成功，刷新权限缓存
                roleMenuService.refreshRolePermsCache(role.getCode());
            }
        }
        return true;
    }

    //获取角色表单数据
    @Override
    public RoleForm getRoleForm(Long roleId) {
        SysRole entity = this.getById(roleId);
        return roleConverter.toForm(entity);
    }

    //获取角色的菜单ID集合
    @Override
    public List<Long> getRoleMenuIds(Long roleId) {
        return roleMenuService.listMenuIdsByRoleId(roleId);
    }

    //修改角色的资源权限
    @Override
    @Transactional
    @CacheEvict(cacheNames = "menu", key = "'routes'")
    public boolean assignMenusToRole(Long roleId, List<Long> menuIds) {
        SysRole role = this.getById(roleId);
        Assert.isTrue(role != null, "角色不存在");

        // 删除角色菜单
        roleMenuService.remove(
                new LambdaQueryWrapper<SysRoleMenu>()
                        .eq(SysRoleMenu::getRoleId, roleId)
        );
        // 新增角色菜单
        if (CollectionUtil.isNotEmpty(menuIds)) {
            List<SysRoleMenu> roleMenus = menuIds
                    .stream()
                    .map(menuId -> new SysRoleMenu(roleId, menuId))
                    .toList();
            roleMenuService.saveBatch(roleMenus);
        }

        // 刷新角色的权限缓存
        roleMenuService.refreshRolePermsCache(role.getCode());

        return true;
    }

}
