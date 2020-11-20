package org.example.module.system.roleresource.service.impl;

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
    public void deleteByRoleId(Long roleId) {
        getBaseMapper().deleteByRoleId(roleId);
    }
}
