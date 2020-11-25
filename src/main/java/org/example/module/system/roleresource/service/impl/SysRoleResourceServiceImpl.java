package org.example.module.system.roleresource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.common.base.BaseService;
import org.example.module.system.roleresource.domain.entity.SysRoleResource;
import org.example.module.system.roleresource.mapper.SysRoleResourceMapper;
import org.example.module.system.roleresource.service.ISysRoleResourceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统角色资源关联 服务实现类
 * </p>
 *
 * @author linzhaoming
 * @since 2020-11-02
 */
@Service
public class SysRoleResourceServiceImpl extends BaseService<SysRoleResourceMapper, SysRoleResource> implements ISysRoleResourceService {

    @Override
    public boolean deleteByRoleId(Long roleId) {
        return this.remove(new LambdaQueryWrapper<SysRoleResource>().eq(SysRoleResource::getRoleId, roleId));
    }

    @Override
    public boolean deleteByResourceId(Long resourceId) {
        return this.remove(new LambdaQueryWrapper<SysRoleResource>().eq(SysRoleResource::getResourceId, resourceId));
    }
}
